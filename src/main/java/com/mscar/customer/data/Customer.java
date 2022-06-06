package com.mscar.customer.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * JPA entity - Customer
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private @Id
    @GeneratedValue Long id;
    @Column(nullable = false)
    private String name;
    private String street;
    private int houseNumber;
    private String zipcode;
    private String place;
    @Column(nullable = false)
    private String email;
    private int phoneNumber;

    public Customer(String name, String street, int houseNumber, String zipcode,
                    String place, String email, int phoneNumber) {
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
        this.place = place;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
