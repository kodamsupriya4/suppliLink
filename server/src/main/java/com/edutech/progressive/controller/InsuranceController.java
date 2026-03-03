package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Insurance;
import com.edutech.progressive.service.InsuranceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    // GET /insurance
    @GetMapping
    public ResponseEntity<List<Insurance>> getAllInsurances() {
        try {
            return ResponseEntity.ok(insuranceService.getAllInsurances());
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET /insurance/{insuranceID}
    @GetMapping("/{insuranceId}")
    public ResponseEntity<Insurance> getInsuranceById(@PathVariable int insuranceId) {
        try {
            Insurance ins = insuranceService.getInsuranceById(insuranceId);
            return (ins != null) ? ResponseEntity.ok(ins) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // POST /insurance
    @PostMapping
    public ResponseEntity<Integer> createInsurance(@RequestBody Insurance insurance) {
        try {
            int id = insuranceService.addInsurance(insurance);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT /insurance/{insuranceID}
    @PutMapping("/{insuranceId}")
    public ResponseEntity<Void> updateInsurance(@PathVariable int insuranceId, @RequestBody Insurance insurance) {
        try {
            insurance.setInsuranceId(insuranceId);
            insuranceService.updateInsurance(insurance);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE /insurance/{insuranceID}
    @DeleteMapping("/{insuranceId}")
    public ResponseEntity<Void> deleteInsurance(@PathVariable int insuranceId) {
        try {
            insuranceService.deleteInsurance(insuranceId);
            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
} 