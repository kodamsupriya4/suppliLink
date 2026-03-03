package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Shipment;
import com.edutech.progressive.repository.ShipmentRepository;
import com.edutech.progressive.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    // Optional to avoid context failures in certain tests
    @Autowired(required = false)
    private com.edutech.progressive.repository.InsuranceRepository insuranceRepository;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public List<Shipment> getAllShipments() throws SQLException {
        try {
            return new ArrayList<>(shipmentRepository.findAll());
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to fetch shipments", ex);
        }
    }

    @Override
    public Shipment getShipmentById(int shipmentId) throws SQLException {
        try {
            return shipmentRepository.findByShipmentId(shipmentId);
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to fetch shipment id: " + shipmentId, ex);
        }
    }

    @Override
    public int addShipment(Shipment shipment) throws SQLException {
        try {
            return shipmentRepository.save(shipment).getShipmentId();
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to add shipment", ex);
        }
    }

    @Override
    public void updateShipment(Shipment shipment) throws SQLException {
        try {
            shipmentRepository.save(shipment);
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to update shipment id: " + shipment.getShipmentId(), ex);
        }
    }

    @Override
    public void deleteShipment(int shipmentId) throws SQLException {
        try {
            // Day 11: delete insurances associated with this shipment first
            if (insuranceRepository != null) {
                insuranceRepository.deleteByShipmentId(shipmentId);
            }
            shipmentRepository.deleteById(shipmentId);
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to delete shipment id: " + shipmentId, ex);
        }
    }
} 