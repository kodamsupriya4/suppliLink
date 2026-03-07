package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.exception.NoWarehouseFoundForSupplierException;
import com.edutech.progressive.service.WarehouseService;
import com.edutech.progressive.service.impl.WarehouseServiceImplJpa;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

// import java.util.List;


// @RestController
// @RequestMapping("warehouse")
// public class WarehouseController {

//     public ResponseEntity<List<Warehouse>> getAllWarehouses() {
//         return null;
//     }

//     public ResponseEntity<Warehouse> getWarehouseById(int warehouseId) {
//         return null;
//     }

//     public ResponseEntity<Integer> addWarehouse(Warehouse warehouse) {
//         return null;
//     }

//     public ResponseEntity<Void> updateWarehouse(int warehouseId, Warehouse warehouse) {
//         return null;
//     }

//     public ResponseEntity<Void> deleteWarehouse(int warehouseId) {
//         return null;
//     }


//     public ResponseEntity<List<Warehouse>> getWarehousesBySupplier(int supplierId) {
//         return null;
//     }
// }


// package com.supplylink.controller;

// import com.supplylink.entity.Warehouse;
// import com.supplylink.service.WarehouseService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseServiceImplJpa warehouseServiceJpa;

    @Autowired
    public WarehouseController(WarehouseServiceImplJpa warehouseServiceJpa) {
        this.warehouseServiceJpa = warehouseServiceJpa;
    }


    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        return ResponseEntity.status(200).body(warehouseServiceJpa.getAllWarehouses());
    }


    @GetMapping("/{warehouseId}")
        @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable int warehouseId) {
        Warehouse w = warehouseServiceJpa.getWarehouseById(warehouseId);
        if (w == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(w);
    }

    @PostMapping
    // @PreAuthorize("hasAuthority('USER')")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Integer> addWarehouse(@RequestBody Warehouse warehouse) {
        // if(warehouse.getSupplier()== null) return ResponseEntity.status(404).build();
        int id = warehouseServiceJpa.addWarehouse(warehouse);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }


    @PutMapping("/{warehouseId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Void> updateWarehouse(@PathVariable int warehouseId, @RequestBody Warehouse warehouse) {
        warehouse.setWarehouseId(warehouseId);
        warehouseServiceJpa.updateWarehouse(warehouse);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{warehouseId}")
   
    // @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable int warehouseId) {
        warehouseServiceJpa.deleteWarehouse(warehouseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/supplier/{supplierId}")

    public ResponseEntity<List<Warehouse>> getWarehousesBySupplier(@PathVariable int supplierId)  {
        List<Warehouse> list;
        try {
            list = warehouseServiceJpa.getWarehouseBySupplier(supplierId);
            return ResponseEntity.status(200).body(list);
        } catch (NoWarehouseFoundForSupplierException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            return ResponseEntity.status(404).build();
        }
        // return ResponseEntity.noContent().build();
        // return ResponseEntity.ok(list);
    }

    // @ExceptionHandler(RuntimeException.class)
    // public ResponseEntity<String> handleException(RuntimeException e){
    //     return ResponseEntity.status(500).body(e.toString());
    // }
} 