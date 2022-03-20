package com.restaurant.exercice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.restaurant.exercice.enumeration.OrderStatus;
import com.restaurant.exercice.enumeration.UnPaidOrderState;
import com.restaurant.exercice.model.Order;
import com.restaurant.exercice.model.Restaurant;
import com.restaurant.exercice.model.Waiter;
import com.restaurant.exercice.service.OrderService;
import com.restaurant.exercice.service.RestaurantService;
import com.restaurant.utils.builder.OrderBuilder;
import com.restaurant.utils.builder.RestaurantBuilder;
import com.restaurant.utils.builder.WaiterBuilder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(classes = { ExerciceApplication.class }, webEnvironment = WebEnvironment.MOCK)
public class EpinglageTestIntegration {
    @Autowired
    OrderService orderService;

    @Autowired
    RestaurantService restaurantService;

    // ÉTANT DONNE une commande épinglée
    // QUAND on l'enregistre dans la BDD
    // ALORS la commande existe bien dans la BDD et est bien épinglée
    @Test
    public void testPinnedOrderWhenRetrieveFromDBhasSameUnpaidStatus() throws Exception {
        // Given
        Order order = new OrderBuilder().withTotalPrice(67).withDate(LocalDateTime.now())
                .withOrderStatus(OrderStatus.UNPAID).build();

        // When
        orderService.saveOrder(order);
        Order orderFromDB = orderService.getOrderById(order.getId()).get();

        // Then
        assertEquals(order.getId(), orderFromDB.getId());
        assertEquals(UnPaidOrderState.PINNED, order.getUnPaidOrderState());
        assertEquals(order.getUnPaidOrderState(), orderFromDB.getUnPaidOrderState());
    }

    // ÉTANT DONNE un restaurant avec une commande épinglée sauvegardé en DB
    // QUAND on récupère le restaurant depuis la DB et qu'on consulte la liste des
    // commandes à transmettre du restaurant
    // ALORS elle la commande épinglée y figure
    @Test
    public void testSaveRestaurantWithPinnedOrderThenGetRestaurantFromDBStillHasPinnedOrder() {
        // Given
        List<Order> orders = new ArrayList<Order>();
        Order order = new OrderBuilder().withOrderStatus(OrderStatus.UNPAID)
                .withDate(LocalDateTime.now().minusDays(20)).build();
        orders.add(order);

        List<Waiter> waiters = new ArrayList<Waiter>();
        Waiter waiter = new WaiterBuilder().withOrders(orders).build();
        waiters.add(waiter);

        Restaurant restaurant = new RestaurantBuilder().withWaiters(waiters).build();
        restaurantService.save(restaurant);

        // When
        Restaurant restaurantFromDB = restaurantService.getRestaurantById(restaurant.getId());
        List<Order> pinnedOrder = restaurantFromDB.retrieveOrdersToTransferToAuthorities()
                .stream()
                .filter(orderFromDB -> orderFromDB.getId().equals(order.getId()))
                .collect(Collectors.toList());

        // Then
        assertTrue(order.getUnPaidOrderState().isToTransferToAuthorities());
        assertTrue(restaurant.retrieveOrdersToTransferToAuthorities().contains(order));
        assertTrue(pinnedOrder.size() > 0);
    }

}
