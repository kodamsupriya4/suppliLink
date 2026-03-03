package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Shipment;
import com.edutech.progressive.service.ShipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    // GET /shipment
    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        try {
            return ResponseEntity.ok(shipmentService.getAllShipments());
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET /shipment/{shipmentID}
    @GetMapping("/{shipmentId}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable int shipmentId) {
        try {
            Shipment s = shipmentService.getShipmentById(shipmentId);
            return (s != null) ? ResponseEntity.ok(s) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // POST /shipment
    @PostMapping
    public ResponseEntity<Integer> createShipment(@RequestBody Shipment shipment) {
        try {
            int id = shipmentService.addShipment(shipment);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT /shipment/{shipmentID}
    @PutMapping("/{shipmentId}")
    public ResponseEntity<Void> updateShipment(@PathVariable int shipmentId, @RequestBody Shipment shipment) {
        try {
            shipment.setShipmentId(shipmentId);
            shipmentService.updateShipment(shipment);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE /shipment/{shipmentID}
    @DeleteMapping("/{shipmentId}")
    public ResponseEntity<Void> deleteShipment(@PathVariable int shipmentId) {
        try {
            shipmentService.deleteShipment(shipmentId);
            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
} 