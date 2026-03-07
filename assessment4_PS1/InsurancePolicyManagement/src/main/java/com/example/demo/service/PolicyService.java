package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.dto.PolicyRequestDTO;
import com.example.demo.dto.PolicyResponseDTO;

public interface PolicyService {

    PolicyResponseDTO createPolicy(PolicyRequestDTO dto);

    PolicyResponseDTO getPolicyById(long id);

    List<PolicyResponseDTO> getAllPolicies();

    PolicyResponseDTO updatePolicy(long id, PolicyRequestDTO dto);

    void cancelPolicy(long id);

    List<PolicyResponseDTO> getPoliciesByType(String type);

    List<PolicyResponseDTO> getPoliciesByPremiumRange(double min, double max);

    Page<PolicyResponseDTO> getPoliciesWithPagination(int page, int size, String sortBy, String direction);

}