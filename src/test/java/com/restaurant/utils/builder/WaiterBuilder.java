package com.restaurant.utils.builder;

import java.util.List;

import com.restaurant.exercice.model.Order;
import com.restaurant.exercice.model.Waiter;
import com.restaurant.utils.generator.OrderGenerator;

public class WaiterBuilder {
    private Waiter waiter;

    public WaiterBuilder() {
        waiter = new Waiter("", "");
    }

    public WaiterBuilder withPaidOrders(int nbOfOrders, int orderPrice) {
        List<Order> orders = OrderGenerator.stubsPaidAndTotalPrice(nbOfOrders, orderPrice);

        for (Order order : orders) {
            this.waiter.addOrder(order);
        }

        return this;
    }

    public WaiterBuilder withName(String name) {
        waiter.setName(name);
        return this;
    }

    public WaiterBuilder withFirstName(String firstName) {
        waiter.setFirstName(firstName);
        return this;
    }

    public WaiterBuilder withOrders(List<Order> orders) {
        waiter.setOrders(orders);
        return this;
    }

    public Waiter build() {
        return this.waiter;
    }

}
