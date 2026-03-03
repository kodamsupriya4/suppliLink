package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.edutech.progressive.dao.WarehouseDAO;
import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.service.WarehouseService;

public class WarehouseServiceImplJdbc implements WarehouseService {
    @Autowired
    WarehouseDAO warehouseDAO;
    
    public WarehouseServiceImplJdbc(WarehouseDAO warehouseDAO) {
        this.warehouseDAO = warehouseDAO;
    }
    @Override
    public List<Warehouse> getAllWarehouses() throws SQLException {
        return warehouseDAO.getAllWarehouse();
    }

    @Override
    public List<Warehouse> getWarehousesSortedByCapacity() throws SQLException {
        List<Warehouse> w = getAllWarehouses();
        w.sort(Comparator.comparingInt(Warehouse::getCapacity));
        return w;
    }
    @Override
    public int addWarehouse(Warehouse warehouse) throws SQLException {
       return warehouseDAO.addWarehouse(warehouse);
    }

} 