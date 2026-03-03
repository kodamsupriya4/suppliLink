package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Supplier;

public class SupplierDAOImpl implements SupplierDAO {
    Connection connection;

    public SupplierDAOImpl() {
        try {
            connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addSupplier(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier (supplier_name, email, username, password, phone, address, role) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getEmail());
            ps.setString(3, supplier.getUsername());
            ps.setString(4, supplier.getPassword());
            ps.setString(5, supplier.getPhone());
            ps.setString(6, supplier.getAddress());
            ps.setString(7, supplier.getRole());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    supplier.setSupplierId(id);
                    return id;
                }
            }
        }
        return -1;
    }

    @Override
    public Supplier getSupplierById(int supplierId) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supplier_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, supplierId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToSupplier(rs);
                }
            }
        }
        return null; // not found
    }


    @Override
    public void updateSupplier(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET supplier_name = ?, email = ?, username = ?, password = ?, " +
                     "phone = ?, address = ?, role = ? WHERE supplier_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getEmail());
            ps.setString(3, supplier.getUsername());
            ps.setString(4, supplier.getPassword()); // if using hashes, update accordingly
            ps.setString(5, supplier.getPhone());
            ps.setString(6, supplier.getAddress());
            ps.setString(7, supplier.getRole());
            ps.setInt(8, supplier.getSupplierId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteSupplier(int supplierId) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supplier_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, supplierId);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Supplier> getAllSuppliers() throws SQLException {
        String sql = "SELECT supplier_id, supplier_name, email, username, password, phone, address, role FROM supplier";
        List<Supplier> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRowToSupplier(rs));
            }
        }
        return list;
    }

    // --- helper mapper ---
    private Supplier mapRowToSupplier(ResultSet rs) throws SQLException {
        Supplier s = new Supplier();
        s.setSupplierId(rs.getInt("supplier_id"));
        s.setSupplierName(rs.getString("supplier_name"));
        s.setEmail(rs.getString("email"));
        s.setUsername(rs.getString("username"));
        s.setPassword(rs.getString("password"));
        s.setPhone(rs.getString("phone"));
        s.setAddress(rs.getString("address"));
        s.setRole(rs.getString("role"));
        return s;
    }
} 