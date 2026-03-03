package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.exception.SupplierAlreadyExistsException;
import com.edutech.progressive.exception.SupplierDoesNotExistException;
import com.edutech.progressive.service.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    @Qualifier("supplierServiceImplJpa")
    private SupplierService supplierServiceJpa;

    @Autowired
    @Qualifier("supplierServiceImplArraylist")
    private SupplierService supplierArray;

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        try {
            return ResponseEntity.ok(supplierServiceJpa.getAllSuppliers());
        } catch (SQLException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable int supplierId) {
        try {
            Supplier s = supplierServiceJpa.getSupplierById(supplierId);
            return ResponseEntity.ok(s);
        } catch (SupplierDoesNotExistException dne) {
            return ResponseEntity.badRequest().build(); // Day 9: BAD_REQUEST if supplier does not exist
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addSupplier(@RequestBody Supplier supplier) {
        try {
            return ResponseEntity.status(201).body(supplierServiceJpa.addSupplier(supplier));
        } catch (SupplierAlreadyExistsException dup) {
            return ResponseEntity.badRequest().build(); // Day 9: BAD_REQUEST on duplicate user/email
        } catch (SQLException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<Void> updateSupplier(@PathVariable int supplierId, @RequestBody Supplier supplier) {
        try {
            supplier.setSupplierId(supplierId);
            supplierServiceJpa.updateSupplier(supplier);
            return ResponseEntity.ok().build();
        } catch (SupplierAlreadyExistsException dup) {
            return ResponseEntity.badRequest().build();
        } catch (SQLException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable int supplierId) {
        try {
            supplierServiceJpa.deleteSupplier(supplierId);
            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Supplier>> getAllSuppliersFromArrayList() {
        try {
            return ResponseEntity.ok(supplierArray.getAllSuppliers());
        } catch (SQLException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/fromArrayList/all")
    public ResponseEntity<List<Supplier>> getAllSuppliersSortedByNameFromArrayList() {
        try {
            return ResponseEntity.ok(supplierArray.getAllSuppliersSortedByName());
        } catch (SQLException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/toArrayList")
    public ResponseEntity<Integer> addSupplierToArrayList(@RequestBody Supplier supplier) {
        try {
            int size = supplierArray.addSupplier(supplier);
            return ResponseEntity.status(HttpStatus.CREATED).body(size);
        } catch (SQLException e) {
            return ResponseEntity.status(500).build();
        }
    }
}