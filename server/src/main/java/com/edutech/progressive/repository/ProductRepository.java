package com.edutech.progressive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.progressive.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query("Select p from Product p where p.productId = :productId")
    Product findByProductId(@Param("productId") int productId);


    List<Product> findAllByWarehouse_WarehouseId(@Param("warehouseId") int warehouseId);
    
    @Modifying
    @Transactional
        @EntityGraph(attributePaths = "warehouse")
    void deleteByWarehouse_WarehouseId(@Param("warehouseId")int warehouseId);

    @Modifying
    @Transactional
        @EntityGraph(attributePaths = "supplier")
    void deleteByWarehouse_Supplier_SupplierId(@Param("supplierId") int supplierId);
} 