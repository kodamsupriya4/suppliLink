package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Shipment;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

    Shipment findByShipmentId(int shipmentId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Shipment s WHERE s.warehouse.warehouseId = :warehouseId")
    void deleteByWarehouseId(@Param("warehouseId") int warehouseId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Shipment s WHERE s.product.productId = :productId")
    void deleteByProductId(@Param("productId") int productId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Shipment s WHERE s.warehouse.warehouseId IN (" +
           " SELECT w.warehouseId FROM Warehouse w WHERE w.supplierId = :supplierId)")
    void deleteBySupplierId(@Param("supplierId") int supplierId);
} 