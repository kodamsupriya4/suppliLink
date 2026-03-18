import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { BehaviorSubject, Observable } from 'rxjs';
import { SupplyLinkService } from '../../services/supplylink.service';
import { Supplier } from '../../types/Supplier';


@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.scss']
})
export class SupplierComponent implements OnInit {
  supplierForm!: FormGroup;

  private successSubject = new BehaviorSubject<string | null>(null);
  private errorSubject = new BehaviorSubject<string | null>(null);
  success$: Observable<string | null> = this.successSubject.asObservable();
  error$: Observable<string | null> = this.errorSubject.asObservable();

  constructor(private fb: FormBuilder, private api: SupplyLinkService) {}

  ngOnInit(): void {
    this.supplierForm = this.fb.group({
      supplierName: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      phone: [''],
      address: [''],
      username: ['', [Validators.required, this.noSpecialCharacters()]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      role: ['', [Validators.required]]
    });

    this.supplierForm.valueChanges.subscribe(() => {
      this.successSubject.next(null);
      this.errorSubject.next(null);
    });
  }

  noSpecialCharacters() {
    const pattern = /^[A-Za-z0-9_]+$/;
    return (control: FormControl) => {
      const value = control?.value ?? '';
      return pattern.test(value) ? null : { specialChars: true };
    };
  }

  onSubmit(): void {
    if (this.supplierForm.invalid) {
      this.errorSubject.next('Please fix the highlighted errors and try again.');
      return;
    }
    const payload = this.supplierForm.getRawValue() as Supplier;
    this.api.addSupplier(payload).subscribe({
      next: () => this.successSubject.next('Supplier created successfully!'),
      error: (err) => {
        console.error(err);
        this.errorSubject.next('Failed to create supplier.');
      }
    });
  }

  get supplierName() { return this.supplierForm.get('supplierName') as FormControl; }
  get email() { return this.supplierForm.get('email') as FormControl; }
  get username() { return this.supplierForm.get('username') as FormControl; }
  get password() { return this.supplierForm.get('password') as FormControl; }
  get role() { return this.supplierForm.get('role') as FormControl; }
} 