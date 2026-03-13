import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-warehouse',
  templateUrl: './warehouse.component.html',
})
export class WarehouseComponent implements OnInit {
  warehouseForm!: FormGroup;

  ngOnInit(): void {
    this.warehouseForm = new FormGroup({
      warehouseId: new FormControl(''),
      supplierId: new FormControl('', [Validators.required, Validators.min(1)]),
      warehouseName: new FormControl('', Validators.required),
      location: new FormControl(''),
      capacity: new FormControl('', [Validators.required, Validators.min(0)])
    });
  }

  onSubmit() {
    console.log("Warehouse Form Submitted:", this.warehouseForm.value);
  }
} 