package com.mscar.customer;

import com.mscar.customer.data.Customer;
import com.mscar.customer.exception.CustomerApiException;
import com.mscar.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Customer controller
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Retrieve customer from H2 database for provided customer id
     * @param id
     * @return Customer
     */
    @GetMapping("/{id}")
    Customer getCustomer(@PathVariable Long id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new CustomerApiException(HttpStatus.NOT_FOUND.value()));
    }

    /**
     * Create new customer in H2 database
     * @param customer
     * @return Customer
     */
    @PostMapping("")
    Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Update customerin H2 database for provided customer id
     * @param inputCustomer
     * @param id
     * @return Customer
     */
    @PutMapping("/{id}")
    Customer updateCustomer(@RequestBody Customer inputCustomer, @PathVariable Long id) {

        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(inputCustomer.getName());
                    customer.setEmail(inputCustomer.getEmail());
                    customer.setHouseNumber(inputCustomer.getHouseNumber());
                    customer.setStreet(inputCustomer.getStreet());
                    customer.setZipcode(inputCustomer.getZipcode());
                    customer.setPlace(inputCustomer.getPlace());
                    customer.setPhoneNumber(inputCustomer.getPhoneNumber());
                    return customerRepository.save(customer);
                }).orElseThrow(() ->
                        new CustomerApiException(HttpStatus.NOT_FOUND.value()));
    }

    /**
     * Delete customer from H2 database for provided customer id
     * @param id
     */
    @DeleteMapping("/{id}")
    void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}
