package com.restaurant.utils.generator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.restaurant.exercice.model.Customer;
import com.restaurant.utils.builder.CustomerBuilder;

public class CustomerGenerator {
    public static List<Customer> stubs(int nbOfCustomers) {
        return IntStream.range(0, nbOfCustomers).mapToObj(nb -> {
            return new CustomerBuilder().withFirstName("CustomerFirstName" + nb).withName("CustomerName" + nb).build();
        }).collect(Collectors.toList());
    }
}
