package com.restaurant.exercice;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.restaurant.exercice.enumeration.OrderStatus;
import com.restaurant.exercice.model.Order;
import com.restaurant.exercice.model.Restaurant;
import com.restaurant.exercice.model.Waiter;
import com.restaurant.utils.builder.OrderBuilder;
import com.restaurant.utils.builder.RestaurantBuilder;
import com.restaurant.utils.builder.WaiterBuilder;

import org.junit.jupiter.api.Test;

public class EpinglageTest {
    // ÉTANT DONNE un serveur ayant pris une commande
    // QUAND il la déclare comme non-payée
    // ALORS cette commande est marquée comme épinglée
    @Test
    public void testWhenWaiterDeclareNonPaidOrderIsPinned() {
        // Given
        List<Order> orders = new ArrayList<Order>();
        Order order = new OrderBuilder().build();
        orders.add(order);
        Waiter waiter = new WaiterBuilder().withOrders(orders).build();

        // When
        order.setOrderStatus(OrderStatus.UNPAID);

        // Then
        assertTrue(order.getUnPaidOrderState().isPinned());
    }

    // ÉTANT DONNE un serveur ayant épinglé une commande
    // QUAND elle date d'il y a au moins 15 jours
    // ALORS cette commande est marquée comme à transmettre gendarmerie
    @Test
    public void testLongPinnedOrderHasToBeMarkToBeSendToTheAuthorities() {
        // Given
        List<Order> orders = new ArrayList<Order>();
        Order order = new OrderBuilder().withOrderStatus(OrderStatus.UNPAID).build();
        orders.add(order);
        Waiter waiter = new WaiterBuilder().withOrders(orders).build();

        // When
        order.setDate(LocalDateTime.now().minusDays(15));

        // Then
        assertTrue(order.getUnPaidOrderState().isToTransferToAuthorities());
    }

    // ÉTANT DONNE une commande à transmettre gendarmerie
    // QUAND on consulte la liste des commandes à transmettre du restaurant
    // ALORS elle y figure
    @Test
    public void testOrderIsPinnedAndAppearInTheRestaurantPinnedOrdersList() {
        // Given
        List<Order> orders = new ArrayList<Order>();
        Order order = new OrderBuilder().withOrderStatus(OrderStatus.UNPAID)
                .withDate(LocalDateTime.of(2020, 1, 1, 1, 1, 1)).build();
        orders.add(order);

        List<Waiter> waiters = new ArrayList<Waiter>();
        Waiter waiter = new WaiterBuilder().withOrders(orders).build();
        waiters.add(waiter);

        Restaurant restaurant = new RestaurantBuilder().withWaiters(waiters).build();

        // When
        List<Order> ordersToTransferToAuthorities = restaurant.retrieveOrdersToTransferToAuthorities();

        // Then
        assertTrue(ordersToTransferToAuthorities.contains(order));
    }

    // ÉTANT DONNE une commande à transmettre gendarmerie
    // QUAND elle est marquée comme transmise à la gendarmerie
    // ALORS elle ne figure plus dans la liste des commandes à transmettre du
    // restaurant
    @Test
    public void testOrderTranferredToAuthoritiesIsNotInTheListToTransfer() {
        // Given
        List<Order> orders = new ArrayList<Order>();
        Order order = new OrderBuilder().withOrderStatus(OrderStatus.UNPAID)
                .withDate(LocalDateTime.of(2020, 1, 1, 1, 1, 1)).build();
        orders.add(order);

        List<Waiter> waiters = new ArrayList<Waiter>();
        Waiter waiter = new WaiterBuilder().withOrders(orders).build();
        waiters.add(waiter);

        Restaurant restaurant = new RestaurantBuilder().withWaiters(waiters).build();

        // When
        order.tranferToAuthorities();

        // Then
        assertFalse(restaurant.retrieveOrdersToTransferToAuthorities().contains(order));

    }

}
