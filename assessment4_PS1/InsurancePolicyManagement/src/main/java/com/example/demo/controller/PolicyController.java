package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.PolicyRequestDTO;
import com.example.demo.dto.PolicyResponseDTO;
import com.example.demo.service.PolicyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @PostMapping
    public PolicyResponseDTO createPolicy(@Valid @RequestBody PolicyRequestDTO dto) {
        return policyService.createPolicy(dto);
    }

    @GetMapping
    public List<PolicyResponseDTO> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    @GetMapping("/{id}")
    public PolicyResponseDTO getPolicyById(@PathVariable long id) {
        return policyService.getPolicyById(id);
    }

    @PutMapping("/{id}")
    public PolicyResponseDTO updatePolicy(@PathVariable long id,
                                          @RequestBody PolicyRequestDTO dto) {
        return policyService.updatePolicy(id, dto);
    }

    @DeleteMapping("/{id}")
    public String cancelPolicy(@PathVariable long id) {
        policyService.cancelPolicy(id);
        return "Policy Cancelled Successfully";
    }

}