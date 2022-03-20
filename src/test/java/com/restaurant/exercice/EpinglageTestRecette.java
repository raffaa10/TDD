package com.restaurant.exercice;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.restaurant.exercice.enumeration.OrderStatus;
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
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = ExerciceApplication.class)
public class EpinglageTestRecette {

        @LocalServerPort
        private int port;

        @Autowired
        TestRestTemplate restTemplate;

        @Autowired
        private RestaurantService restaurantService;

        @Autowired
        private OrderService orderService;

        // ÉTANT DONNE une commande à transmettre gendarmerie
        // QUAND on passe le status à payée
        // ALORS elle ne figure plus dans la liste des commandes à transmettre à
        // gendarmerie du restaurant
        @Test
        public void testOrderToTransferWhenIsPaidThenIsNotInListToTransferToTheAuthorities() {
                // Given
                List<Order> orders = new ArrayList<Order>();
                Order order = new OrderBuilder()
                                .withOrderStatus(OrderStatus.UNPAID)
                                .withDate(LocalDateTime.now().minusDays(20))
                                .build();
                orders.add(order);

                List<Waiter> waiters = new ArrayList<Waiter>();
                Waiter waiter = new WaiterBuilder().withOrders(orders).build();
                waiters.add(waiter);

                Restaurant restaurant = new RestaurantBuilder().withWaiters(waiters).build();
                restaurantService.save(restaurant);

                // When
                restTemplate.put("/order/" + order.getId() + "/pay", null);
                Restaurant retrievedRestaurant = restTemplate
                                .getForEntity("/restaurant/" + restaurant.getId(), Restaurant.class)
                                .getBody();

                List<Order> ordersToTransfer = retrievedRestaurant.retrieveOrdersToTransferToAuthorities()
                                .stream()
                                .filter(orderFiltered -> orderFiltered.getId().equals(order.getId()))
                                .collect(Collectors.toList());

                // Then
                assertFalse(ordersToTransfer.size() > 0);
        }

        // ÉTANT DONNE une commande transmise à la gendarmerie
        // QUAND on passe le status à payée
        // ALORS elle est toujours en statut "transmise à la gendarmerie"
        @Test
        public void testOrderTransferredToAuthoritiesWhenIsPaidThenStillTransferred() {
                // Given
                LocalDateTime outDated = LocalDateTime.now().minusDays(20);
                Order order = new OrderBuilder().withOrderStatus(OrderStatus.UNPAID).withDate(outDated).build();
                order.tranferToAuthorities();
                orderService.saveOrder(order);

                // When
                restTemplate.put("/order/" + order.getId() + "/pay", null);
                Order retrievedOrder = restTemplate.getForEntity("/order/" + order.getId(), Order.class).getBody();

                // Then
                assertTrue(retrievedOrder.getUnPaidOrderState().isTransferredToAuthorities());
        }
}
