package com.restaurant.exercice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.restaurant.exercice.enumeration.OrderStatus;

@Entity
public class Waiter extends Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<Order>();

    public Waiter() {
    }

    public Waiter(String name, String firstName) {
        super(name, firstName);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        // Add the created order to the order list owned by the waiter
        orders.add(order);
    }

    public int getDailyTotalPaidOrders() {
        return this.orders.stream()
                .filter(order -> order.getOrderStatus().isPaid())
                .map(currentOrder -> currentOrder.getTotalPrice())
                .reduce(0, (total, orderPrice) -> orderPrice + total);
    }

    public int getDailyTotalUnPaidOrders() {
        return 0;
    }

    public void declareUnPaidOrder(Order order) {
        order.setOrderStatus(OrderStatus.UNPAID);
    }

    public void declarePaidOrder(Order order) {
        order.setOrderStatus(OrderStatus.PAID);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
