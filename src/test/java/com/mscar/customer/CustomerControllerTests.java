package com.mscar.customer;

import com.mscar.customer.data.Customer;
import com.mscar.customer.exception.CustomerApiException;
import com.mscar.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerControllerTests {
    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerRepository customerRepository;

    @Test
    public void testRetrieveCustomerDetails() {
        var customer = createCustomerData();

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        var result = customerController.getCustomer(1L);

        assertEquals(2312434, result.getPhoneNumber());
        assertEquals("LJ2344", result.getZipcode());
    }

    @Test
    public void testCreateCustomer() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        var customer = createCustomerData();

        when(customerRepository.save(any())).thenReturn(customer);

        var result = customerController.createCustomer(customer);

        assertEquals(2, result.getHouseNumber());
    }

    @Test
    public void testUpdateCustomer() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        var customer = createCustomerData();
        customer.setName("madhavi satam");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any())).thenReturn(customer);

        var result = customerController.updateCustomer(customer, 1L);

        assertEquals("madhavi satam", result.getName());
    }

    @Test
    public void testDeleteCustomer() {

        doNothing().when(customerRepository).deleteById(1L);

        customerController.deleteCustomer(1L);

        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testRetrieveCustomerDetails_Exception() {

        when(customerRepository.findById(2L)).thenThrow(new CustomerApiException(HttpStatus.NOT_FOUND.value()));

        CustomerApiException thrown = Assertions
                .assertThrows(CustomerApiException.class, () -> {
                    var result = customerController.getCustomer(2L);
                }, "CarApiException error was expected");

        assertEquals(HttpStatus.NOT_FOUND.value(), thrown.getStatus());
    }

    @Test
    public void testDeleteCustomer_Exception() {

        doThrow(new CustomerApiException(HttpStatus.NOT_FOUND.value())).when(customerRepository).deleteById(2L);

        CustomerApiException thrown = Assertions
                .assertThrows(CustomerApiException.class, () -> {
                    customerController.deleteCustomer(2L);
                }, "CustomerApiException error was expected");

        assertEquals(HttpStatus.NOT_FOUND.value(), thrown.getStatus());
    }

    private Customer createCustomerData() {
        return Customer.builder()
                .name("madhavi")
                .email("test123@hmail.com")
                .houseNumber(2)
                .street("test")
                .place("test place")
                .zipcode("LJ2344")
                .phoneNumber(2312434).build();
    }
}


