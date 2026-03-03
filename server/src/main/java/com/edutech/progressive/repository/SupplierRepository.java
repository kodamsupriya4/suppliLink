package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Supplier;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Transactional
    void deleteBySupplierId(@Param("supplierId") int supplierId);

    Supplier findBySupplierId(@Param("supplierId") int supplierId);

    Supplier findByUsername(@Param("username") String username);

    Supplier findByEmail(@Param("email") String email);
} 