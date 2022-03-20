package com.restaurant.utils.builder;

import com.restaurant.exercice.model.Customer;

public class CustomerBuilder {
    private Customer customer;

    public CustomerBuilder() {
        this.customer = new Customer("", "");
    }

    public CustomerBuilder withName(String name) {
        customer.setName(name);
        return this;
    }

    public CustomerBuilder withFirstName(String firstName) {
        customer.setFirstName(firstName);
        return this;
    }

    public Customer build() {
        return this.customer;
    }

}
