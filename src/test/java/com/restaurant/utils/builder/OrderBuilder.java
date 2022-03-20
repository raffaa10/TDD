package com.restaurant.utils.builder;

import java.time.LocalDateTime;

import com.restaurant.exercice.enumeration.OrderStatus;
import com.restaurant.exercice.model.Order;
import com.restaurant.exercice.model.Table;

public class OrderBuilder {
    private Order order;

    public OrderBuilder() {
        order = new Order(0, new Table());
    }

    public OrderBuilder withTotalPrice(int totalPrice) {
        this.order.setTotalPrice(totalPrice);
        return this;
    }

    public OrderBuilder withOrderStatus(OrderStatus orderStatus) {
        this.order.setOrderStatus(orderStatus);
        return this;
    }

    public OrderBuilder withDate(LocalDateTime date) {
        this.order.setDate(date);
        return this;
    }

    public OrderBuilder withTable(Table table) {
        this.order.setTable(table);
        return this;
    }

    public OrderBuilder withId(int id) {
        this.order.setId(id);
        return this;
    }

    public Order build() {
        return this.order;
    }

}
