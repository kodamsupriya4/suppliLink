package com.edutech.progressive.service;

import com.edutech.progressive.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts() throws SQLException;

    Product getProductById(int productId) throws SQLException;

    int addProduct(Product product) throws SQLException;

    void updateProduct(Product product) throws SQLException;

    void deleteProduct(int productId) throws SQLException;

    // Day-8: default in interface; implemented in JPA impl
    default List<Product> getAllProductByWarehouse(int warehouseId) throws SQLException {
        return List.of();
    }
} 