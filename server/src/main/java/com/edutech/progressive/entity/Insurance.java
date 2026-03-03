package com.edutech.progressive.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "insurance")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int insuranceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id", nullable = false)
    @JsonIgnore
    private Shipment shipment;

    @Column(name = "insurance_provider", nullable = false)
    private String insuranceProvider;


    @Column(name = "insurance_coverage_amount", nullable = false)
    private Long insuranceCoverageAmount;

   
    @Transient
    private Integer shipmentId;

    public Insurance() {}

    public Insurance(int insuranceId, Shipment shipment, String insuranceProvider, Long insuranceCoverageAmount) {
        this.insuranceId = insuranceId;
        this.shipment = shipment;
        this.insuranceProvider = insuranceProvider;
        this.insuranceCoverageAmount = insuranceCoverageAmount;
    }

    public int getInsuranceId() { return insuranceId; }
    public void setInsuranceId(int insuranceId) { this.insuranceId = insuranceId; }

    public Shipment getShipment() { return shipment; }
    public void setShipment(Shipment shipment) { this.shipment = shipment; }

    public String getInsuranceProvider() { return insuranceProvider; }
    public void setInsuranceProvider(String insuranceProvider) { this.insuranceProvider = insuranceProvider; }

    public Long getInsuranceCoverageAmount() { return insuranceCoverageAmount; }
    public void setInsuranceCoverageAmount(Long insuranceCoverageAmount) { this.insuranceCoverageAmount = insuranceCoverageAmount; }

    public Integer getShipmentId() {
        if (this.shipment != null) {
            return this.shipment.getShipmentId();
        }
        return this.shipmentId;
    }

    public void setShipmentId(Integer shipmentId) {
        this.shipmentId = shipmentId;
        if (shipmentId != null) {
            Shipment s = new Shipment();
            s.setShipmentId(shipmentId);
            this.shipment = s; // set association so JPA writes shipment_id
        }
    }
}