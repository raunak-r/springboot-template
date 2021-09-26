package com.raunak.springbootdemootp.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import com.raunak.springbootdemootp.auth.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);

    @Query(value="select * from customer", nativeQuery = true)
    List<Customer> findAll();

}
