package com.edutech.progressive.entity;

import javax.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Warehouse implements Comparable<Warehouse> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int warehouseId;

    @Column(name = "supplier_id", insertable = false, updatable = false)
    private int supplierId;

    private String warehouseName;
    private String location;
    private int capacity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    @JsonIgnore
    private Supplier supplier;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Product> products;

    public Warehouse() {}

    public Warehouse(int warehouseId, int supplierId, String warehouseName, String location, int capacity) {
        this.warehouseId = warehouseId;
        this.supplierId = supplierId;
        this.warehouseName = warehouseName;
        this.location = location;
        this.capacity = capacity;
    }

    public int getWarehouseId() { return warehouseId; }
    public void setWarehouseId(int warehouseId) { this.warehouseId = warehouseId; }

    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

    public String getWarehouseName() { return warehouseName; }
    public void setWarehouseName(String warehouseName) { this.warehouseName = warehouseName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    @Override
    public int compareTo(Warehouse o) {
        return Integer.compare(this.capacity, o.capacity);
    }
} 