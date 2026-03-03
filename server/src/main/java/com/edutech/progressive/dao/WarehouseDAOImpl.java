package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Warehouse;

public class WarehouseDAOImpl implements WarehouseDAO {
    Connection connection;

    public WarehouseDAOImpl() {
        try {
            connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addWarehouse(Warehouse warehouse) throws SQLException {
        String query = "INSERT INTO warehouse(supplier_id, warehouse_name, location, capacity) VALUES(?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, warehouse.getSupplierId());
            ps.setString(2, warehouse.getWarehouseName());
            ps.setString(3, warehouse.getLocation());
            ps.setInt(4, warehouse.getCapacity());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    warehouse.setWarehouseId(id);
                    return id;
                }
            }
        }
        return -1;
    }

    @Override
    public Warehouse getWarehouseById(int warehouseId) throws SQLException {
        String sql = "SELECT warehouse_id, supplier_id, warehouse_name, location, capacity FROM warehouse WHERE warehouse_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, warehouseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToWarehouse(rs);
                }
            }
        }
        return null;
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) throws SQLException {
        String sql = "UPDATE warehouse SET supplier_id = ?, warehouse_name = ?, location = ?, capacity = ? WHERE warehouse_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, warehouse.getSupplierId());
            ps.setString(2, warehouse.getWarehouseName());
            ps.setString(3, warehouse.getLocation());
            ps.setInt(4, warehouse.getCapacity());
            ps.setInt(5, warehouse.getWarehouseId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteWarehouse(int warehouseId) throws SQLException {
        String sql = "DELETE FROM warehouse WHERE warehouse_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, warehouseId);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Warehouse> getAllWarehouse() throws SQLException {
        String sql = "SELECT warehouse_id, supplier_id, warehouse_name, location, capacity FROM warehouse";
        List<Warehouse> warehouses = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                warehouses.add(mapRowToWarehouse(rs));
            }
        }
        return warehouses;
    }

    // --- helper ---
    private Warehouse mapRowToWarehouse(ResultSet rs) throws SQLException {
        Warehouse w = new Warehouse();
        w.setWarehouseId(rs.getInt("warehouse_id"));
        w.setSupplierId(rs.getInt("supplier_id"));
        w.setWarehouseName(rs.getString("warehouse_name"));
        w.setLocation(rs.getString("location"));
        w.setCapacity(rs.getInt("capacity"));
        return w;
    }
} 