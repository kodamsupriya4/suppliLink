package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Warehouse;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    Warehouse findByWarehouseId(int warehouseId);
    List<Warehouse> findAllBySupplierId(int supplierId);

    List<Warehouse> findAllBySupplier_SupplierId(int supplierId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Warehouse w WHERE w.supplierId = :supplierId")
    void deleteBySupplierId(@Param("supplierId") int supplierId);
} 