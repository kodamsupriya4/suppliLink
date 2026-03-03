package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        try {
            List<Warehouse> list = warehouseService.getAllWarehouses();
            return ResponseEntity.ok(list);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{warehouseId}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable int warehouseId) {
        try {
            Warehouse w = warehouseService.getWarehouseById(warehouseId);
            return (w != null) ? ResponseEntity.ok(w) : ResponseEntity.notFound().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addWarehouse(@RequestBody Warehouse warehouse) {
        try {
            int id = warehouseService.addWarehouse(warehouse);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{warehouseId}")
    public ResponseEntity<Void> updateWarehouse(@PathVariable int warehouseId, @RequestBody Warehouse warehouse) {
        try {
            warehouse.setWarehouseId(warehouseId);
            warehouseService.updateWarehouse(warehouse);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{warehouseId}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable int warehouseId) {
        try {
            warehouseService.deleteWarehouse(warehouseId);
            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


@GetMapping("/supplier/{supplierId}")
public ResponseEntity<?> getWarehousesBySupplier(@PathVariable int supplierId) {
    try {
        List<Warehouse> list = warehouseService.getWarehouseBySupplier(supplierId);
        return ResponseEntity.ok(list);
    } catch (com.edutech.progressive.exception.NoWarehouseFoundForSupplierException noWh) {
        // Day 9: BAD_REQUEST if no warehouses for the supplier
        return ResponseEntity.badRequest().build();
    } catch (SQLException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

} 