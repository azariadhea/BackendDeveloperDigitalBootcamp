//http://localhost:8080/customer

package com.multipolar.bootcamp.kyc.controller;

import com.multipolar.bootcamp.kyc.domain.Customer;
import com.multipolar.bootcamp.kyc.dto.ErrorMessage;
import com.multipolar.bootcamp.kyc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorMessage> validationErrors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setCode("VALIDATION_ERROR");
                errorMessage.setMessage(error.getDefaultMessage());
                validationErrors.add(errorMessage);
            }
            return ResponseEntity.badRequest().body(validationErrors);
        }
        Customer createdCustomer = customerService.createOrUpdateCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    @GetMapping
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        customer.setId(id);
        return customerService.createOrUpdateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable String id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

    ///http://localhost:8080/customer/nik/1212121212121212
    @GetMapping("/nik/{nik}")
    public ResponseEntity<Customer> getCustomerByNik(@PathVariable String nik) {
        Optional<Customer> customer = customerService.getCustomerByNik(nik);
        return customer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    ///http://localhost:8080/customer/name/azaria
    @GetMapping("/name/{name}")
    public List<Customer> getByName(@PathVariable String name){
        return customerService.getByName(name);
    }
}
