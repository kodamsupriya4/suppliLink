package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("supplierServiceImplArraylist")
public class SupplierServiceImplArraylist implements SupplierService {

private final List<Supplier> s = new ArrayList<>();

    @Override
    public List<Supplier> getAllSuppliers() {
        return s;
    }

    @Override
    public int addSupplier(Supplier supplier) {
        s.add(supplier);
        return s.size();
    }

    @Override
    public List<Supplier> getAllSuppliersSortedByName() {
        Collections.sort(s);
        return s;
    }

    @Override
    public void emptyArrayList() {
        s.clear();
    }
} 