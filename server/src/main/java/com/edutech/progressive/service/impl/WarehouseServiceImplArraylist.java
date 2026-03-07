// package com.edutech.progressive.service.impl;

// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.Comparator;
// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.edutech.progressive.entity.Warehouse;
// import com.edutech.progressive.service.WarehouseService;

// @Service("warehouseServiceArrayList")
// public class WarehouseServiceImplArraylist implements WarehouseService  {

//     List<Warehouse> warehouses= new ArrayList<>();



//     public int addWarehouse(Warehouse w){

//         warehouses.add(w);
//         return w.getSupplierId();
//     }

//     public List<Warehouse> getAllWarehouses(){
//         warehouses.sort(Comparator.comparing(Warehouse::getCapacity));
//         Collections.sort(warehouses);
//         return warehouses;
//         // return null;
//     }



//     public List<Warehouse> getWarehousesSortedByCapacity(){
//         List<Warehouse> w = new ArrayList<>(getAllWarehouses());
//         w.sort(Comparator.comparing(Warehouse::getCapacity).reversed());
//         Collections.sort(warehouses);

//         return warehouses;
//         // return null;
//     }

//     public List<Warehouse> getWarehousesSortedByName(){
//         List<Warehouse> w = new ArrayList<>(getAllWarehouses());
//         w.sort(Comparator.comparing(Warehouse::getWarehouseName));

//         return w;
//         // return null;
//     }


//     public void emptyArrayList(){
//         warehouses.clear();
//     }


// } 