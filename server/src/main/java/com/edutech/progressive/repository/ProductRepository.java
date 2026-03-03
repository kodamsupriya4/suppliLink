package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Single product by domain ID
    Product findByProductId(int productId);

    // Association-based (JOIN) – keep for completeness
    List<Product> findAllByWarehouse_WarehouseId(int warehouseId);

    // ✅ FK-based finder (NO JOIN) – critical for Day 8 tests seeded via JDBC
    @Query("SELECT p FROM Product p WHERE p.warehouseId = :warehouseId")
    List<Product> findAllByWarehouseId(@Param("warehouseId") int warehouseId);

    // (Optional) Count via association
    int countByWarehouse_WarehouseId(Integer warehouseId);

    // ✅ FK-based delete (NO JOIN)
    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.warehouseId = :warehouseId")
    void deleteByWarehouseId(@Param("warehouseId") int warehouseId);

    // ✅ FK-based supplier delete (NO JOIN on Supplier)
    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.warehouseId IN (" +
           " SELECT w.warehouseId FROM Warehouse w WHERE w.supplierId = :supplierId)")
    void deleteBySupplierId(@Param("supplierId") int supplierId);
} 