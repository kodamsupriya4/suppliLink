package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.dao.WarehouseDAO;
import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.service.WarehouseService;


// @Service("warehouseServiceJdbc")
public class WarehouseServiceImplJdbc  implements WarehouseService {


    // private WarehouseServiceImplJdbc warehouseServiceImplJdbc;
    private WarehouseDAO warehouseDAO;

    @Autowired
   public WarehouseServiceImplJdbc(WarehouseDAO warehouseDAO) {
    this.warehouseDAO = warehouseDAO;
}


    @Override
    public List<Warehouse> getAllWarehouses() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getAllWarehouses'");
        return warehouseDAO.getAllWarehouse();
    }



 @Override
    public int addWarehouse(Warehouse warehouse) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationExce
        // ption("Unimplemented method 'addWarehouse'");
        try {
            return warehouseDAO.addWarehouse(warehouse);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Warehouse> getWarehousesSortedByCapacity() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getWarehousesSortedByCapacity'");
        List<Warehouse> w = getAllWarehouses();
        Collections.sort(w);
        return w;
    }

} 