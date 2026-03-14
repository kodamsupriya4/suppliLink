import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-warehouse',
  templateUrl: './warehouse.component.html',
})
export class WarehouseComponent implements OnInit {
  warehouseForm!: FormGroup;
  submitted = false;

  // Backend error state
  backendError: string | null = null;
  backendFieldErrors: Record<string, string> = {};
  successMessage: string | null = null;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.warehouseForm = this.fb.group({
      supplierId: [null, [Validators.required, Validators.min(1)]], // Supplier id cannot be null
      warehouseName: ['', [Validators.required]],
      location: ['', [Validators.required]],
      capacity: [0, [Validators.required, Validators.min(0)]], // non-negative
    });
  }

  ctrl(name: string): AbstractControl {
    return this.warehouseForm.get(name) as AbstractControl;
  }

  onSubmit(): void {
    this.submitted = true;
    this.backendError = null;
    this.backendFieldErrors = {};
    this.successMessage = null;

    if (this.warehouseForm.invalid) {
      this.warehouseForm.markAllAsTouched();
      return;
    }

    // Simulate backend validation (e.g., duplicate warehouse name)
    const backend = this.simulateBackendError(this.warehouseForm.value);
    if (backend) {
      if (backend.global) this.backendError = backend.global;
      if (backend.fields) this.backendFieldErrors = backend.fields;

      // Attach field-level backend errors to controls
      Object.keys(this.backendFieldErrors).forEach(key => {
        const c = this.warehouseForm.get(key);
        if (c) c.setErrors({ ...(c.errors || {}), backend: true });
      });
      return;
    }

    // Success
    this.successMessage = 'Warehouse registered successfully.';
    console.log('Warehouse payload:', this.warehouseForm.value);
    this.warehouseForm.reset({ supplierId: null, warehouseName: '', location: '', capacity: 0 });
    this.submitted = false;
  }

  /**
   * Simulates server-side checks:
   * - Rejects duplicate warehouseName
   * - (Optionally) could reject unrealistic capacities, etc.
   */
  private simulateBackendError(value: any):
    | { global?: string; fields?: Record<string, string> }
    | null {
    const duplicateNames = ['Central Warehouse', 'Main Hub', 'North DC'].map(x => x.toLowerCase());
    const fields: Record<string, string> = {};

    if (duplicateNames.includes((value.warehouseName || '').toLowerCase())) {
      fields['warehouseName'] = 'Warehouse name already exists. Choose a different name.';
    }

    if (Object.keys(fields).length > 0) {
      return {
        global: 'Validation failed on the server. Please resolve the errors below.',
        fields,
      };
    }
    return null;
  }
} 