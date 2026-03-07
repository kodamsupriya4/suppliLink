package com.edutech.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.entity.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{

    Shipment findByShipmentId(int shipmentId);

    void deleteByWarehouse_WarehouseId(int warehouseId);

    void deleteByProduct_ProductId(int productId);

    void deleteByWarehouse_Supplier_SupplierId(int supplierId);
} 