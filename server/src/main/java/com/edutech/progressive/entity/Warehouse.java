package com.edutech.progressive.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
// import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import org.springframework.beans.factory.annotation.Autowired;


@Entity
@Table(name = "warehouse")
public class Warehouse implements Comparable<Warehouse>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private int warehouseId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    // @JsonIgnore
    private Supplier supplier;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "location")
    private String location;

    
    @Column(name = "warehouse_name")
    private String warehouseName;

public Warehouse(int warehouseId, Supplier supplier, int capacity, String location, String warehouseName) {
        this.warehouseId = warehouseId;
        this.supplier = supplier;
        this.capacity = capacity;
        this.location = location;
        this.warehouseName = warehouseName;
    }
    public Warehouse(){
        
    }
public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
@Override
public int compareTo(Warehouse o) {
    // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    return -Integer.compare(this.capacity, o.capacity);
}
public String getWarehouseName() {
    return warehouseName;
}
public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName;
}
} 