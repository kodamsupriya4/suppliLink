package com.edutech.progressive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.edutech.progressive.entity.Product;
import com.edutech.progressive.entity.Shipment;
// import com.edutech.progressive.entity.Warehouse;
import com.edutech.progressive.repository.ShipmentRepository;
import com.edutech.progressive.service.ShipmentService;

@Service
public class ShipmentServiceImpl implements ShipmentService  {


    private final ShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public List<Shipment> getAllShipments() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getAllShipments'");
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment getShipmentById(int shipmentId) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getShipmentById'");
        return shipmentRepository.findById(shipmentId).orElse(null);
    }

    @Override
    public int addShipment(Shipment shipment) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'addShipment'");
        Shipment s = shipmentRepository.save(shipment);
        return s != null ? s.getShipmentId() : -1;
    }


    @Override
    public void updateShipment( Shipment shipment) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'updateShipment'");
        Shipment oldShipment = shipmentRepository.findById(shipment.getShipmentId()).orElse(null);
        if(oldShipment==null) return;
        oldShipment.setDestinationLocation(shipment.getDestinationLocation());
        oldShipment.setExpectedDeliveryDate(shipment.getExpectedDeliveryDate());
        oldShipment.setShipmentDate(shipment.getShipmentDate());
        oldShipment.setSourceLocation(shipment.getSourceLocation());
        oldShipment.setStatus(shipment.getStatus());
        oldShipment.setWarehouse(shipment.getWarehouse());
        oldShipment.setSourceLocation(shipment.getSourceLocation());

        shipmentRepository.save(oldShipment);
        
    }

    @Override
    public void deleteShipment(int shipmentId) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'deleteShipment'");
        // shipmentRepository.deleteById(shipmentId);
        // Warehouse w = shipmentRepository.findByShipmentId(shipmentId).getWarehouse();
        // Product p = shipmentRepository.findByShipmentId(shipmentId).getProduct();
        // shipmentRepository.deleteByProduct_ProductId(p.getProductId());
        // shipmentRepository.deleteByWarehouse_WarehouseId(p.getProductId());
        // shipmentRepository.deleteByWarehouse_Supplier_SupplierId(p.getWarehouse().getSupplier().getSupplierId());
        shipmentRepository.deleteById(shipmentId);
        
    }
} 