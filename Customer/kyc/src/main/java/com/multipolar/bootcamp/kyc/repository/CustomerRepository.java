package com.multipolar.bootcamp.kyc.repository;

import com.multipolar.bootcamp.kyc.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findByNik(String nik);

    List<Customer> findAllByFirstName(String firstname);

    Optional<Customer> findByEmail(String email);

    @Query("{ 'firstName' : { $regex: ?0, $options: 'i' } }")
    List<Customer> getByName(String name);

}
