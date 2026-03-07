package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.service.SupplierService;


@Service("supplierServiceArraylist")
public class SupplierServiceImplArraylist implements SupplierService  {

    private List<Supplier> suppliers = new ArrayList<>();

    @Autowired
    public SupplierServiceImplArraylist() {
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getAllSuppliers'");
        return suppliers;
    }

    @Override
    public int addSupplier(Supplier supplier) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'addSupplier'");
        suppliers.add(supplier);
        return suppliers.size();
    }

    @Override
    public List<Supplier> getAllSuppliersSortedByName() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getAllSuppliersSortedByName'");
        Collections.sort(suppliers);
        return suppliers;
    }
} 