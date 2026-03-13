import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  ValidationErrors,
  ValidatorFn,
  Validators
} from '@angular/forms';
import { BehaviorSubject, Observable } from 'rxjs';
import { Supplier } from '../../types/Supplier';

@Component({
  selector: 'app-supplier',

  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.scss']
})
export class SupplierComponent implements OnInit {

  supplier: Supplier = new Supplier(
    1,
    'Jane Doe',
    'jane.doe@example.com',
    '9876543210',
    'Chennai',
    'janedoe',
    'Password@123',
    'USER'
  );


  supplierForm!: FormGroup;


  private supplierSuccessSubject = new BehaviorSubject<string | null>(null);
  private supplierErrorSubject = new BehaviorSubject<string | null>(null);

  supplierSuccess$: Observable<string | null> = this.supplierSuccessSubject.asObservable();
  supplierError$: Observable<string | null> = this.supplierErrorSubject.asObservable();

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {

    this.supplierForm = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      username: ['', [Validators.required, this.noSpecialCharacters()]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });


    this.supplierForm.valueChanges.subscribe(() => {
      this.supplierSuccessSubject.next(null);
      this.supplierErrorSubject.next(null);
    });
  }


  
  noSpecialCharacters(): ValidatorFn {
    const pattern = /^[A-Za-z0-9_]+$/;
    return (control: AbstractControl): ValidationErrors | null => {
      const value = (control?.value ?? '') as string;
      return pattern.test(value) ? null : { specialChars: true };
    };
  }

  
  onSubmit(): void {
    if (this.supplierForm.invalid) {
      this.supplierErrorSubject.next('Please fix the highlighted errors and try again.');
      return;
    }

    const payload = this.supplierForm.getRawValue();


    
    this.supplier = new Supplier(
      this.supplier.supplierId ?? 1,
      payload.name,
      payload.email,
      this.supplier.phone,
      this.supplier.address,
      payload.username,
      payload.password,
      this.supplier.role
    );

    this.supplierSuccessSubject.next('Supplier form submitted successfully!');
    this.supplierErrorSubject.next(null);
  }

  // Getters for template usage
  get name() { return this.supplierForm.get('name') as FormControl; }
  get email() { return this.supplierForm.get('email') as FormControl; }
  get username() { return this.supplierForm.get('username') as FormControl; }
  get password() { return this.supplierForm.get('password') as FormControl; }
} 