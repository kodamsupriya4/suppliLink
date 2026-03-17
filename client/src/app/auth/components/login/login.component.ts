import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService) {}

  ngOnInit(): void {
    // Initialize with empty fields as Day-22 tests expect
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
      return; // Day-22 test asserts we do NOT call service when invalid
    }
    const payload = this.loginForm.getRawValue();
    this.authService.login(payload).subscribe({
      next: () => {},
      error: () => {},
    });
  }

  // Getters (handy in template/tests)
  get username() { return this.loginForm.get('username') as FormControl; }
  get password() { return this.loginForm.get('password') as FormControl; }
} 