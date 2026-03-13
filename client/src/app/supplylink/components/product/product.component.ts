import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
})
export class ProductComponent implements OnInit {
  productForm!: FormGroup;

  ngOnInit(): void {
    this.productForm = new FormGroup({
      productId: new FormControl(''),
      warehouseId: new FormControl('', [Validators.required, Validators.min(1)]),
      productName: new FormControl('', Validators.required),
      productDescription: new FormControl(''),
      quantity: new FormControl('', [Validators.required, Validators.min(0)]),
      price: new FormControl('', [Validators.required, Validators.min(1)])
    });
  }

  onSubmit() {
    const product = this.productForm.value;
    console.log("Product submitted:", product);

    // Further processing can be added here
  }
} 