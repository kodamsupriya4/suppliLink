import { Component } from '@angular/core';
import { Supplier } from '../../types/Supplier';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-supplierarray',
  imports: [CommonModule],
  standalone: true,
  templateUrl: './supplierarray.component.html',
  styleUrls: ['./supplierarray.component.css']
})

export class SupplierArrayComponent {
  supplierList: Supplier[] = [

    
    new Supplier(1, 'Jessica Alba', 'jessica@gmail.com', '9000000001', 'Hyderabad', 'jessica', 'pass1', 'Distributor'),
    new Supplier(2, 'John Wane', 'johnwane@gmail.com', '9000000002', 'Chennai', 'johnwane', 'pass2', 'Wholesaler'),
    new Supplier(3, 'Kristan', 'kristan@gmail.com', '9000000003', 'Bangalore', 'kristan', 'pass3', 'Retailer')

  ];

  
getSupplierHtml(): string {
    let html = '';

    for (let i = 0; i < this.supplierList.length; i++) {

      const s = this.supplierList[i];
      const name = (s as any).supplierName;
      html += `<p>${name} ${s.email}</p>`;

    }

    return html;
  }

} 