package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PolicyRequestDTO;
import com.example.demo.dto.PolicyResponseDTO;
import com.example.demo.mapper.PolicyMapper;
import com.example.demo.model.Customer;
import com.example.demo.model.Policy;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PolicyRepository;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public PolicyResponseDTO createPolicy(PolicyRequestDTO dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Policy policy = new Policy();

        policy.setPolicyNumber(dto.getPolicyNumber());
        policy.setPolicyType(dto.getPolicyType());
        policy.setPremiumAmount(dto.getPremiumAmount());
        policy.setCoverageAmount(dto.getCoverageAmount());
        policy.setStartDate(dto.getStartDate());
        policy.setEndDate(dto.getEndDate());
        policy.setStatus("ACTIVE");

        policy.setCustomer(customer);

        Policy savedPolicy = policyRepository.save(policy);

        return PolicyMapper.toDTO(savedPolicy);
    }

    // Get Policy by ID
    @Override
    public PolicyResponseDTO getPolicyById(long id) {

        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        return PolicyMapper.toDTO(policy);
    }

    @Override
    public List<PolicyResponseDTO> getAllPolicies() {

        List<Policy> policies = policyRepository.findAll();

        return policies.stream()
                .map(PolicyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PolicyResponseDTO updatePolicy(long id, PolicyRequestDTO dto) {

        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        policy.setPolicyNumber(dto.getPolicyNumber());
        policy.setPolicyType(dto.getPolicyType());
        policy.setPremiumAmount(dto.getPremiumAmount());
        policy.setCoverageAmount(dto.getCoverageAmount());
        policy.setStartDate(dto.getStartDate());
        policy.setEndDate(dto.getEndDate());

        Policy updatedPolicy = policyRepository.save(policy);

        return PolicyMapper.toDTO(updatedPolicy);
    }

    @Override
    public void cancelPolicy(long id) {

        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        policy.setStatus("CANCELLED");

        policyRepository.save(policy);
    }

    @Override
    public List<PolicyResponseDTO> getPoliciesByType(String type) {

        List<Policy> policies = policyRepository.findByPolicyType(type);

        return policies.stream()
                .map(PolicyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PolicyResponseDTO> getPoliciesByPremiumRange(double min, double max) {

        List<Policy> policies = policyRepository.findByPremiumAmountBetween(min, max);

        return policies.stream()
                .map(PolicyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PolicyResponseDTO> getPoliciesWithPagination(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Policy> policyPage = policyRepository.findAll(pageable);

        return policyPage.map(PolicyMapper::toDTO);
    }

}