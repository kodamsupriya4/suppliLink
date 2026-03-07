package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Insurance;
import com.edutech.progressive.service.InsuranceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/insurance")
public class InsuranceController {
    private InsuranceService insuranceService;
    @Autowired
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping
    public ResponseEntity<List<Insurance>> getAllInsurances() {
        return ResponseEntity.status(200).body(insuranceService.getAllInsurances());
    }

    @GetMapping("/{insuranceId}")
    public ResponseEntity<Insurance> getInsuranceById(@PathVariable int insuranceId) {
        Insurance i = insuranceService.getInsuranceById(insuranceId);
        if(i==null) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(insuranceService.getInsuranceById(insuranceId));
    }

    @PostMapping
    public ResponseEntity<Integer> createInsurance(@RequestBody Insurance insurance) {
        return ResponseEntity.status(201).body(insuranceService.addInsurance(insurance));
    }


    @PutMapping("/{insuranceId}")    
    public ResponseEntity<Void> updateInsurance(@PathVariable int insuranceId, @RequestBody Insurance insurance) {
        Insurance i = insuranceService.getInsuranceById(insuranceId);
        if(i==null) return ResponseEntity.status(404).build();
        insurance.setInsuranceId(insuranceId);
        insuranceService.updateInsurance(insurance);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{insuranceId}")
    public ResponseEntity<Void> deleteInsurance(@PathVariable int insuranceId) {
        Insurance i = insuranceService.getInsuranceById(insuranceId);
        if(i==null) return ResponseEntity.status(404).build();
        insuranceService.deleteInsurance(insuranceId);
        return ResponseEntity.status(200).build();
    }
} 