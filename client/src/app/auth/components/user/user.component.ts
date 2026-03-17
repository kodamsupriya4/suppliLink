import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

/**
 * Minimal "User" form just to satisfy Day-22 tests:
 * - ngOnInit initializes with empty fields
 * - Contains basic fields (you can expand later)
 */
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
})
export class UserComponent implements OnInit {
  userForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    // Initialize with empty values as tests require
    this.userForm = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  onSubmit(): void {
    if (this.userForm.invalid) return;
    // For Day-22 test, no specific submit behavior required
    const payload = this.userForm.getRawValue();
    console.log('User form submitted:', payload);
  }

  get name() { return this.userForm.get('name') as FormControl; }
  get email() { return this.userForm.get('email') as FormControl; }
  get username() { return this.userForm.get('username') as FormControl; }
  get password() { return this.userForm.get('password') as FormControl; }
} 