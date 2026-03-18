import { Component, OnInit } from '@angular/core';
import { SupplyLinkService } from '../../services/supplylink.service';
import { Supplier } from '../../types/Supplier';
import { Warehouse } from '../../types/Warehouse';
import { Product } from '../../types/Product';
import { AuthService } from '../../../auth/services/auth.service';

@Component({
  selector: 'app-supplylink-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  suppliers: Supplier[] = [];
  warehouses: Warehouse[] = [];
  products: Product[] = [];

  role: string | null = null;
  loading = false;
  error: string | null = null;

  constructor(private api: SupplyLinkService, private auth: AuthService) {}

  ngOnInit(): void {
    this.role = this.auth.getRole();
    if (this.role === 'ADMIN') {
      this.loadAdminData(); // tests expect this to be called for ADMIN
    }
  }

  // Make this public so the spec can call it directly
  loadAdminData(): void {
    this.loading = true;
    this.error = null;

    // Subscribe individually so the arrays are set synchronously for specs using `of(...)`
    this.api.getAllSuppliers().subscribe({
      next: (suppliers) => { this.suppliers = suppliers || []; },
      error: (err) => { console.error(err); this.error = 'Failed to load data.'; }
    });

    this.api.getAllWarehouses().subscribe({
      next: (warehouses) => { this.warehouses = warehouses || []; },
      error: (err) => { console.error(err); this.error = 'Failed to load data.'; }
    });

    this.api.getAllProducts().subscribe({
      next: (products) => { this.products = products || []; },
      error: (err) => { console.error(err); this.error = 'Failed to load data.'; },
      complete: () => { this.loading = false; }
    });
  }
} 