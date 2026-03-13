import { Component } from '@angular/core';
import { Warehouse } from '../../types/Warehouse';

@Component({
  selector: 'app-warehousesample',
  standalone: true,
  imports: [],
  templateUrl: './warehousesample.component.html',
  styleUrls: ['./warehousesample.component.css']
})
export class WarehouseSampleComponent {

  warehouse: Warehouse = new Warehouse(
    1,
    "12",
    "Flamingo",
    "Nevada",
    1000
  );

} 