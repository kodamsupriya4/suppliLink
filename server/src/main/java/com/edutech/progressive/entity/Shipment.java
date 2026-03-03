package com.edutech.progressive.entity;

import javax.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Shipment") // ensure it matches the test DDL
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shipmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    @JsonIgnore
    private Warehouse warehouse;

    @Temporal(TemporalType.DATE)
    private Date shipmentDate;

    @Temporal(TemporalType.DATE)
    private Date expectedDeliveryDate;

    // Map to snake_case DB columns used in tests
    @Column(name = "source_location")
    private String sourceLocation;

    @Column(name = "destination_location")
    private String destinationLocation;

    private String status;

    public Shipment() {}

    public Shipment(int shipmentId, Product product, Warehouse warehouse, Date shipmentDate,
                    Date expectedDeliveryDate, String sourceLocation, String destinationLocation, String status) {
        this.shipmentId = shipmentId;
        this.product = product;
        this.warehouse = warehouse;
        this.shipmentDate = shipmentDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.sourceLocation = sourceLocation;
        this.destinationLocation = destinationLocation;
        this.status = status;
    }

    public int getShipmentId() { return shipmentId; }
    public void setShipmentId(int shipmentId) { this.shipmentId = shipmentId; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Warehouse getWarehouse() { return warehouse; }
    public void setWarehouse(Warehouse warehouse) { this.warehouse = warehouse; }

    public Date getShipmentDate() { return shipmentDate; }
    public void setShipmentDate(Date shipmentDate) { this.shipmentDate = shipmentDate; }

    public Date getExpectedDeliveryDate() { return expectedDeliveryDate; }
    public void setExpectedDeliveryDate(Date expectedDeliveryDate) { this.expectedDeliveryDate = expectedDeliveryDate; }

    public String getSourceLocation() { return sourceLocation; }
    public void setSourceLocation(String sourceLocation) { this.sourceLocation = sourceLocation; }

    public String getDestinationLocation() { return destinationLocation; }
    public void setDestinationLocation(String destinationLocation) { this.destinationLocation = destinationLocation; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
} 