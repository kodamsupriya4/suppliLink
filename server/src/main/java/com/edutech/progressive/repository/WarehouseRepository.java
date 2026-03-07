package com.edutech.progressive.repository;

import java.util.List;

// import o rg.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.progressive.entity.Warehouse;


@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{


    // @Query("Select * from Warehouse w join fetch  w.supplier where w.supplier_id = :supplierId")
    // @EntityGraph(attributePaths = "supplier")

    List<Warehouse> findAllBySupplier_SupplierId(int supplierId);
    @Modifying
    @Transactional
    // @Query("delete from warehouse w where w.supplier.supplier_id = :supplierId")
    void deleteBySupplier_SupplierId(int supplierId);
    
} 