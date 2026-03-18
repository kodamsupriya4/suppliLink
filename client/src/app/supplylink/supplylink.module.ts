import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';

import { SharedModule } from '../shared/shared.module';


import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SupplierComponent } from './components/supplier/supplier.component';
import { WarehouseComponent } from './components/warehouse/warehouse.component';
import { ProductComponent } from './components/product/product.component';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'supplier', component: SupplierComponent },
  { path: 'warehouse', component: WarehouseComponent },
  { path: 'product', component: ProductComponent }, // ensure ProductComponent exists; otherwise remove
];

@NgModule({
  declarations: [
    SupplierComponent,
    WarehouseComponent,
    ProductComponent,
    DashboardComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    SharedModule,
    RouterModule.forChild(routes)
  ]
})
export class SupplyLinkModule {} 