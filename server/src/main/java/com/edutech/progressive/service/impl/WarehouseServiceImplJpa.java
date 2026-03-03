package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.repository.ProductRepository;
import com.edutech.progressive.repository.WarehouseRepository;
import com.edutech.progressive.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Primary
@Service("warehouseServiceImplJpa")
public class WarehouseServiceImplJpa implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository; // may be null in 1-arg ctor

    // ✅ Optional field injection to prevent context failure if not scanned in some tests
    @Autowired(required = false)
    private com.edutech.progressive.repository.ShipmentRepository shipmentRepository;

    // Preferred constructor
    @Autowired
    public WarehouseServiceImplJpa(WarehouseRepository warehouseRepository,
                                   ProductRepository productRepository) {
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
    }

    // Overloaded for tests that only pass repo
    public WarehouseServiceImplJpa(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
        this.productRepository = null;
    }

    @Override
    public List<Warehouse> getAllWarehouses() throws SQLException {
        try {
            return new ArrayList<>(warehouseRepository.findAll());
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to fetch warehouses", ex);
        }
    }

    @Override
    public int addWarehouse(Warehouse warehouse) throws SQLException {
        try {
            Warehouse saved = warehouseRepository.save(warehouse);
            return saved.getWarehouseId();
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to add warehouse", ex);
        }
    }

    @Override
    public List<Warehouse> getWarehousesSortedByCapacity() throws SQLException {
        try {
            List<Warehouse> list = new ArrayList<>(warehouseRepository.findAll());
            Collections.sort(list); // Comparable by capacity
            return list;
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to get warehouses sorted by capacity", ex);
        }
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) throws SQLException {
        try {
            warehouseRepository.save(warehouse);
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to update warehouse id: " + warehouse.getWarehouseId(), ex);
        }
    }

    @Override
    @Transactional
    public void deleteWarehouse(int warehouseId) throws SQLException {
        try {
            // Day 10 cascade: shipments -> products -> warehouse (only if beans present)
            if (shipmentRepository != null) {
                shipmentRepository.deleteByWarehouseId(warehouseId);
            }
            if (productRepository != null) {
                productRepository.deleteByWarehouseId(warehouseId);
            }
            warehouseRepository.deleteById(warehouseId);
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to delete warehouse id: " + warehouseId, ex);
        }
    }

    @Override
    public Warehouse getWarehouseById(int warehouseId) throws SQLException {
        try {
            return warehouseRepository.findByWarehouseId(warehouseId);
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to fetch warehouse id: " + warehouseId, ex);
        }
    }

    @Override
    public List<Warehouse> getWarehouseBySupplier(int supplierId) throws SQLException {
        try {
            List<Warehouse> viaFk = warehouseRepository.findAllBySupplierId(supplierId);
            if (viaFk != null && !viaFk.isEmpty()) return new ArrayList<>(viaFk);

            List<Warehouse> viaAssoc = warehouseRepository.findAllBySupplier_SupplierId(supplierId);
            if (viaAssoc != null && !viaAssoc.isEmpty()) return new ArrayList<>(viaAssoc);

            throw new com.edutech.progressive.exception.NoWarehouseFoundForSupplierException(
                    "No warehouses found for supplierId=" + supplierId
            );
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to fetch warehouses for supplier id: " + supplierId, ex);
        }
    }
} 