package com.restaurant.exercice;

import com.restaurant.exercice.enumeration.OrderStatus;
import com.restaurant.exercice.model.Order;
import com.restaurant.exercice.model.Waiter;
import com.restaurant.utils.builder.OrderBuilder;
import com.restaurant.utils.builder.WaiterBuilder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test des serveurs")
public class WaiterTest {

    // ÉTANT DONNÉ un nouveau serveur
    // QUAND on récupére son chiffre d'affaires
    // ALORS celui-ci est à 0
    @Test
    @DisplayName("Un nouveau serveur doit avoir un chiffre d'affaires à zéro")
    public void testNewWaiterShouldHaveDailyPaidOrderToZero() {
        // Given
        Waiter waiter = new WaiterBuilder().build();

        // When
        int dailyTotalPaidOrders = waiter.getDailyTotalPaidOrders();

        // Then
        Assertions.assertEquals(0, dailyTotalPaidOrders);
    }

    // ÉTANT DONNÉ un nouveau serveur
    // QUAND il prend une commande
    // ALORS son chiffre d'affaires est le montant de celle-ci
    @Test
    @DisplayName("Un serveur prend une commande et son chiffre d'affaire est égal au prix de la commande")
    public void testWaiterTakeOrderThenDailyTotalPaidIsEqualToOrderPrice() {
        // Given
        Waiter waiter = new WaiterBuilder().build();
        Order order = new OrderBuilder().withTotalPrice(54).withOrderStatus(OrderStatus.PAID).build();

        // When
        waiter.addOrder(order);

        // Then
        Assertions.assertEquals(order.getTotalPrice(), waiter.getDailyTotalPaidOrders());
    }

    // ÉTANT DONNÉ un serveur ayant déjà pris une commande
    // QUAND il prend une nouvelle commande
    // ALORS son chiffre d'affaires est la somme des deux commandes

    @Test
    @DisplayName("Un serveur avec une commmande, en prend une deuxieme et son chiffre d'affaire est égal aux deux commandes")
    public void testWaiterTakeTwoOrderAndDailyTotalPaidIsEqualsToBothOrder() {
        // Given
        int orderOnePrice = 5;
        int orderTwoPrice = 9;
        int expectedTotal = orderOnePrice + orderTwoPrice;

        Waiter waiter = new WaiterBuilder().withPaidOrders(1, orderOnePrice).build();
        Order order = new OrderBuilder().withTotalPrice(orderTwoPrice).withOrderStatus(OrderStatus.PAID).build();

        // When
        waiter.addOrder(order);

        // Then
        Assertions.assertEquals(expectedTotal, waiter.getDailyTotalPaidOrders());
    }

}
