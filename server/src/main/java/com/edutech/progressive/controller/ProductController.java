package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Product;
import com.edutech.progressive.exception.InsufficientCapacityException;
import com.edutech.progressive.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(@Qualifier("productServiceImplJpa") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            return ResponseEntity.ok(productService.getAllProducts());
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        try {
            Product p = productService.getProductById(productId);
            if (p == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(p);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addProduct(@RequestBody Product product) {
        try {
            int id = productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (InsufficientCapacityException ice) {
            return ResponseEntity.badRequest().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable int productId, @RequestBody Product product) {
        try {
            product.setProductId(productId);
            productService.updateProduct(product);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<Product>> getAllProductByWarehouse(@PathVariable int warehouseId) {
        try {
            return ResponseEntity.ok(productService.getAllProductByWarehouse(warehouseId));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}