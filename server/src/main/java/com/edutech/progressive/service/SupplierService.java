package com.edutech.progressive.service;


import com.edutech.progressive.entity.Supplier;
import com.edutech.progressive.exception.SupplierAlreadyExistsException;
import com.edutech.progressive.exception.SupplierDoesNotExistException;
import java.util.List;

// import org.springframework.stereotype.Service;
// @Service("supplierService")
public interface SupplierService {
    List<Supplier> getAllSuppliers();
    int addSupplier(Supplier supplier) throws SupplierAlreadyExistsException;
    List<Supplier> getAllSuppliersSortedByName();
    default public void emptyArrayList() {
    }

    //Do not implement these methods in SupplierServiceImplArraylist.java class
    default void updateSupplier(Supplier supplier) {
    }
    default void deleteSupplier(int supplierId) throws SupplierDoesNotExistException {
    }
    default Supplier getSupplierById(int supplierId) throws SupplierDoesNotExistException {
        return null;
    }

} 