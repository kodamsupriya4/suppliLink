package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Shipment;
import com.edutech.progressive.service.ShipmentService;
// import com.edutech.progressive.service.impl.ShipmentServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/shipment")
public class ShipmentController {


    private final ShipmentService shipmentService;

    

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        return ResponseEntity.status(200).body(shipmentService.getAllShipments());
    }

    @GetMapping("/{shipmentId}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable int shipmentId) {
        Shipment s = shipmentService.getShipmentById(shipmentId);
        if(s == null) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(s);
    }

    @PostMapping
    public ResponseEntity<Integer> addShipment(@RequestBody Shipment shipment) {
        return ResponseEntity.status(201).body(shipmentService.addShipment(shipment));
    }


    @PutMapping("/{shipmentId}")
    public ResponseEntity<Void> updateShipment(@PathVariable int shipmentId,@RequestBody Shipment shipment) {
        Shipment s = shipmentService.getShipmentById(shipmentId);
        if(s == null) return ResponseEntity.noContent().build(); 
        s.setShipmentId(shipmentId);
        shipmentService.updateShipment(shipment);
        return ResponseEntity.status(200).body(null);
    }

    @DeleteMapping("/{shipmentId}")
    public ResponseEntity<Void> deleteShipment(@PathVariable int shipmentId) {
        Shipment s = shipmentService.getShipmentById(shipmentId);
        if(s == null) return ResponseEntity.noContent().build(); 
        shipmentService.deleteShipment(shipmentId);
        return ResponseEntity.status(200).build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException(RuntimeException e){
        return ResponseEntity.status(500).body(null);
    }
} 