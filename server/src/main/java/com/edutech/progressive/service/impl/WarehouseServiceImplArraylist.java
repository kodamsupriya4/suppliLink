package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.service.WarehouseService;

public class WarehouseServiceImplArraylist implements WarehouseService  {
    public static List<Warehouse> w = new ArrayList<>();
    @Override
    public List<Warehouse> getAllWarehouses() {
        return w;
    }

    @Override
    public int addWarehouse(Warehouse warehouse) {
        w.add(warehouse);
        return w.size();
    }

    @Override
    public List<Warehouse> getWarehousesSortedByCapacity() {
        Collections.sort(w);
        return w;
    }

    public void emptyArrayList(){
        w.clear();
    }

} 