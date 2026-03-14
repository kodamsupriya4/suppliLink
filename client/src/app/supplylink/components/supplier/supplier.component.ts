import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors } from '@angular/forms';

@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
})
export class SupplierComponent {
  supplierForm: FormGroup;
  submitted = false;

  // Simulated backend error state
  backendError: string | null = null;
  backendFieldErrors: Record<string, string> = {};
  successMessage: string | null = null;

  // Patterns per requirements
  // - Names: no special characters
  private readonly noSpecialsPattern = /^[a-zA-Z0-9 ]+$/;
  private readonly usernamePattern = /^[a-zA-Z0-9]+$/; // strictly alphanumeric
  private readonly phonePattern = /^[0-9]{10}$/;

  constructor(private fb: FormBuilder) {
    this.supplierForm = this.fb.group({
      supplierName: ['', [Validators.required, Validators.pattern(this.noSpecialsPattern)]],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern(this.phonePattern)]],
      address: ['', [Validators.required]],
      username: ['', [Validators.required, Validators.pattern(this.usernamePattern)]],
      password: ['', [Validators.required, Validators.minLength(8), this.passwordPolicyValidator]],
      role: ['', [Validators.required]],
    });
  }

  // Convenience getter
  ctrl(name: string): AbstractControl {
    return this.supplierForm.get(name) as AbstractControl;
  }

  // Custom validator: at least one uppercase & one digit
  passwordPolicyValidator(control: AbstractControl): ValidationErrors | null {
    const value = (control.value || '') as string;
    if (!value) return null;
    const hasUpper = /[A-Z]/.test(value);
    const hasDigit = /\d/.test(value);
    return hasUpper && hasDigit ? null : { passwordPolicy: true };
  }

  onSubmit(): void {
    this.submitted = true;
    this.backendError = null;
    this.backendFieldErrors = {};
    this.successMessage = null;

    if (this.supplierForm.invalid) {
      this.supplierForm.markAllAsTouched();
      return;
    }

    // Simulate backend validation
    const backend = this.simulateBackendError(this.supplierForm.value);
    if (backend) {
      // Map backend errors to UI
      if (backend.global) this.backendError = backend.global;
      if (backend.fields) this.backendFieldErrors = backend.fields;

      // Set control errors so red borders and a11y messages appear
      Object.keys(this.backendFieldErrors).forEach(key => {
        const c = this.supplierForm.get(key);
        if (c) {
          const current = c.errors || {};
          c.setErrors({ ...current, backend: true });
        }
      });
      return;
    }

    // On success
    this.successMessage = 'Supplier registered successfully.';
    console.log('Supplier payload:', this.supplierForm.value);
    this.supplierForm.reset();
    this.submitted = false;
  }

  /** Simulates backend validations for duplicate username/email, etc. */
  private simulateBackendError(value: any):
    | { global?: string; fields?: Record<string, string> }
    | null {
    const duplicates = {
      usernames: ['admin', 'supplier', 'manager'],
      emails: ['taken@example.com', 'exists@duplicate.com'],
    };

    const fields: Record<string, string> = {};
    if (duplicates.usernames.includes((value.username || '').toLowerCase())) {
      fields['username'] = 'This username is already taken.';
    }
    if (duplicates.emails.includes((value.email || '').toLowerCase())) {
      fields['email'] = 'This email is already registered.';
    }

    if (Object.keys(fields).length > 0) {
      return {
        global: 'Please resolve the errors below.',
        fields,
      };
    }
    return null;
  }
} 