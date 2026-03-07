package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.CustomerRequestDTO;
import com.example.demo.dto.CustomerResponseDTO;

public interface CustomerService {

    CustomerResponseDTO createCustomer(CustomerRequestDTO dto);

    List<CustomerResponseDTO> getAllCustomers();

    CustomerResponseDTO getCustomerById(Long id);

}