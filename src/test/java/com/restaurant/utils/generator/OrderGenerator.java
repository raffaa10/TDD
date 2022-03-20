package com.restaurant.utils.generator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.restaurant.exercice.enumeration.OrderStatus;
import com.restaurant.exercice.model.Order;
import com.restaurant.utils.builder.OrderBuilder;

public class OrderGenerator {
    public static List<Order> stubsPaidAndTotalPrice(int nbOfOrders, int orderPrice) {
        return Stream.generate(OrderBuilder::new)
                .limit(nbOfOrders)
                .map(order -> order.withTotalPrice(orderPrice).withOrderStatus(OrderStatus.PAID).build())
                .collect(Collectors.toList());
    }
}
