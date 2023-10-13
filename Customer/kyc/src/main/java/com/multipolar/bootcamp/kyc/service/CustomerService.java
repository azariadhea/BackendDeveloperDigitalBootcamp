package com.multipolar.bootcamp.kyc.service;

import com.multipolar.bootcamp.kyc.domain.Customer;
import com.multipolar.bootcamp.kyc.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    public Customer createOrUpdateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomerById(String id) {
        customerRepository.deleteById(id);
    }

    public Optional<Customer> getCustomerByNik(String nik) {
        return customerRepository.findByNik(nik);
    }

    public List<Customer> getByName(String name) {
        return customerRepository.getByName(name);
    }
}

