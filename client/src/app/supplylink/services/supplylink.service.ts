import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Supplier } from '../types/Supplier';
import { Warehouse } from '../types/Warehouse';
import { Product } from '../types/Product';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SupplyLinkService {
  // Change baseUrl if your backend is on a different origin/prefix
  private readonly baseUrl = '';

  constructor(private http: HttpClient) {}

  // -------- SUPPLIER --------
  addSupplier(supplier: Supplier): Observable<any> {
    return this.http.post(`${this.baseUrl}/supplier`, supplier);
  }
  editSupplier(supplier: Supplier): Observable<any> {
    return this.http.put(`${this.baseUrl}/supplier/${supplier.supplierId}`, supplier);
  }
  deleteSupplier(supplierId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/supplier/${supplierId}`);
  }
  getSupplierById(supplierId: number): Observable<Supplier> {
    return this.http.get<Supplier>(`${this.baseUrl}/supplier/${supplierId}`);
  }
  getAllSuppliers(): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(`${this.baseUrl}/supplier`);
  }

  // -------- WAREHOUSE --------
  addWarehouse(warehouse: Warehouse): Observable<any> {
    return this.http.post(`${this.baseUrl}/warehouse`, warehouse);
  }
  editWarehouse(warehouse: Warehouse): Observable<any> {
    return this.http.put(`${this.baseUrl}/warehouse/${warehouse.warehouseId}`, warehouse);
  }
  deleteWarehouse(warehouseId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/warehouse/${warehouseId}`);
  }
  getWarehouseById(warehouseId: number): Observable<Warehouse> {
    return this.http.get<Warehouse>(`${this.baseUrl}/warehouse/${warehouseId}`);
  }
  getAllWarehouses(): Observable<Warehouse[]> {
    return this.http.get<Warehouse[]>(`${this.baseUrl}/warehouse`);
  }
  getWarehousesBySupplier(supplierId: number): Observable<Warehouse[]> {
    return this.http.get<Warehouse[]>(`${this.baseUrl}/warehouse/supplier/${supplierId}`);
  }

  // -------- PRODUCT --------
  addProduct(product: Product): Observable<any> {
    return this.http.post(`${this.baseUrl}/product`, product);
  }
  editProduct(product: Product): Observable<any> {
    return this.http.put(`${this.baseUrl}/product/${product.productId}`, product);
  }
  deleteProduct(productId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/product/${productId}`);
  }
  getProductById(productId: number): Observable<Product> {
    return this.http.get<Product>(`${this.baseUrl}/product/${productId}`);
  }
  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}/product`);
  }
  getAllProductByWarehouse(warehouseId: number | null): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}/product/warehouse/${warehouseId}`);
  }
} 