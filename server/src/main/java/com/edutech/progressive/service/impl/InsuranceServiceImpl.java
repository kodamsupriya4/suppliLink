package com.edutech.progressive.service.impl;

import com.edutech.progressive.entity.Insurance;
import com.edutech.progressive.repository.InsuranceRepository;
import com.edutech.progressive.service.InsuranceService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;

    public InsuranceServiceImpl(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    @Override
    public List<Insurance> getAllInsurances() throws SQLException {
        try {
            return new ArrayList<>(insuranceRepository.findAll());
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to fetch insurances", ex);
        }
    }

    @Override
    public int addInsurance(Insurance insurance) throws SQLException {
        try {
            return insuranceRepository.save(insurance).getInsuranceId();
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to add insurance", ex);
        }
    }

    @Override
    public Insurance getInsuranceById(int insuranceId) throws SQLException {
        try {
            return insuranceRepository.findByInsuranceId(insuranceId);
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to fetch insurance id: " + insuranceId, ex);
        }
    }

    @Override
    public void updateInsurance(Insurance insurance) throws SQLException {
        try {
            insuranceRepository.save(insurance);
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to update insurance id: " + insurance.getInsuranceId(), ex);
        }
    }

    @Override
    public void deleteInsurance(int insuranceId) throws SQLException {
        try {
            insuranceRepository.deleteById(insuranceId);
        } catch (DataAccessException ex) {
            throw new SQLException("Failed to delete insurance id: " + insuranceId, ex);
        }
    }
} 