package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Product;
import com.edutech.progressive.service.impl.ProductServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/product")

public class ProductController {

    private final ProductServiceImplJpa productServiceImplJpa;
    @Autowired
    public ProductController(ProductServiceImplJpa productServiceImplJpa) {
        this.productServiceImplJpa = productServiceImplJpa;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(200).body(productServiceImplJpa.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        return ResponseEntity.status(200).body(productServiceImplJpa.getProductById(productId));
    }

    @PostMapping
    // @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Integer> addProduct(@RequestBody Product product) {
        return ResponseEntity.status(201).body(productServiceImplJpa.addProduct(product));
    }

   @PutMapping("/{productId}")
//    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> updateProduct(@PathVariable int productId, @RequestBody Product product) {
        Product p  = productServiceImplJpa.getProductById(productId);
        productServiceImplJpa.updateProduct(p);
        return ResponseEntity.status(204).body(null);
    }

    @DeleteMapping("/{productId}")
    // @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
        // Product  p = productServiceImplJpa.getProductById(productId);

        // productServiceImplJpa.deleteProduct(p.getWarehouse().getWarehouseId());
        productServiceImplJpa.deleteProduct(productId);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/warehouse/{warehouseId}")
    
    public ResponseEntity<List<Product>> getAllProductByWarehouse(@PathVariable int warehouseId) {
        return ResponseEntity.status(200).body(productServiceImplJpa.getAllProductByWarehouse(warehouseId));
    }
} 