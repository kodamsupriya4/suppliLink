package com.edutech.progressive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Insurance;
import com.edutech.progressive.repository.InsuranceRepository;
import com.edutech.progressive.service.InsuranceService;

@Service
public class InsuranceServiceImpl implements InsuranceService  {


    private final InsuranceRepository insuranceRepo;

    @Autowired
    public InsuranceServiceImpl(InsuranceRepository insuranceRepo) {
        this.insuranceRepo = insuranceRepo;
    }

    @Override
    public List<Insurance> getAllInsurances() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getAllInsurances'");
        return insuranceRepo.findAll();
    }

    @Override
    public int addInsurance(Insurance insurance) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'addInsurance'");
        Insurance a = insuranceRepo.save(insurance);
        return a != null ? a.getInsuranceId() : -1;
    }

    @Override
    public Insurance getInsuranceById(int insuranceId) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getInsuranceById'");
        return insuranceRepo.findById(insuranceId).orElse(null);
    }

    @Override
    public void updateInsurance(Insurance insurance) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'updateInsurance'");
        Insurance old = insuranceRepo.findById(insurance.getInsuranceId()).orElse(null);
        old.setInsuranceCoverageAmount(insurance.getInsuranceCoverageAmount());
        old.setShipment(insurance.getShipment());
        old.setInsuranceProvider(insurance.getInsuranceProvider());
        insuranceRepo.save(old);

    }

    @Override
    public void deleteInsurance(int insuranceId) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'deleteInsurance'");
        insuranceRepo.deleteById(insuranceId);
    }
} 