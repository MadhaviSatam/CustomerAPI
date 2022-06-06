package com.mscar.customer.repository;

import com.mscar.customer.data.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Customer JPA repository
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
