package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Product;

public class ProductDAOImpl implements ProductDAO {
    Connection connection;

    public ProductDAOImpl() {
        try {
            connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addProduct(Product product) throws SQLException {
        String query = "INSERT INTO product(warehouse_id, product_name, product_description, quantity, price) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, product.getWarehouseId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getProductDescription());
            ps.setInt(4, product.getQuantity());
            ps.setLong(5, product.getPrice()); 
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    product.setProductId(id);
                    return id;
                }
            }
        }
        return -1;
    }

    @Override
    public Product getProductById(int productId) throws SQLException {
        String sql = "SELECT * FROM product WHERE product_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToProduct(rs);
                }
            }
        }
        return null;
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE product SET warehouse_id = ?, product_name = ?, product_description = ?, quantity = ?, price = ? WHERE product_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, product.getWarehouseId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getProductDescription());
            ps.setInt(4, product.getQuantity());
            ps.setLong(5, product.getPrice()); // use setLong
            ps.setInt(6, product.getProductId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM product WHERE product_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        String sql = "SELECT product_id, warehouse_id, product_name, product_description, quantity, price FROM product";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                products.add(mapRowToProduct(rs));
            }
        }
        return products;
    }

    // --- helper mapper ---
    private Product mapRowToProduct(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setProductId(rs.getInt("product_id"));
        p.setWarehouseId(rs.getInt("warehouse_id"));
        p.setProductName(rs.getString("product_name"));
        p.setProductDescription(rs.getString("product_description"));
        p.setQuantity(rs.getInt("quantity"));
        p.setPrice(rs.getLong("price"));
        return p;
    }
} 