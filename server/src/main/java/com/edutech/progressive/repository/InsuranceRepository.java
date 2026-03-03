package com.edutech.progressive.repository;

import com.edutech.progressive.entity.Insurance;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {

    Insurance findByInsuranceId(int insuranceId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Insurance i WHERE i.shipment.shipmentId = :shipmentId")
    void deleteByShipmentId(@Param("shipmentId") int shipmentId);
} 