package com.restaurant.utils.generator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.restaurant.exercice.model.Waiter;
import com.restaurant.utils.builder.WaiterBuilder;

public class WaiterGenerator {
    public static List<Waiter> stubs(int nbOfWaiters) {
        return Stream.generate(WaiterBuilder::new)
                .limit(nbOfWaiters)
                .map(waiter -> waiter.build())
                .collect(Collectors.toList());
    }

    public static List<Waiter> stubsWithPaidOrders(int nbOfWaiters, int nbOfOrders, int orderPrice) {
        return Stream.generate(WaiterBuilder::new)
                .limit(nbOfWaiters)
                .map(waiter -> waiter.withPaidOrders(nbOfOrders, orderPrice).build())
                .collect(Collectors.toList());
    }

}
