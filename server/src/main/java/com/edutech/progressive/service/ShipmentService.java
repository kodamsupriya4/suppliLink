package com.edutech.progressive.service;
import com.edutech.progressive.entity.Shipment;
import java.util.List;

// import org.springframework.stereotype.Service;
public interface ShipmentService {
    List<Shipment> getAllShipments();
    Shipment getShipmentById(int shipmentId);
    int addShipment(Shipment shipment);
    void updateShipment(Shipment shipment);
    void deleteShipment(int shipmentId);
    // void updateShipment(int shipId, Shipment shipment);

} 