package com.restaurant.exercice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import com.restaurant.exercice.enumeration.OrderStatus;
import com.restaurant.exercice.model.Order;
import com.restaurant.exercice.repository.OrderRepository;
import com.restaurant.exercice.service.OrderService;
import com.restaurant.utils.builder.OrderBuilder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EpinglageTestSystem {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    // ÉTANT DONNE une commande non payée depuis 14 jours
    // QUAND on la passe à non payée depuis 15 jours
    // ALORS la commande retournée est transmise à la gendarmerie
    @Test
    public void testOrderNonPaidAndAlmostOutdatedWhenIsOutDatedThenOrderIsToTransferToTheAuthorities() {
        // Given
        LocalDateTime almostOutdated = LocalDateTime.now().minusDays(14);
        Integer orderId = 3;
        Order order = new OrderBuilder().withOrderStatus(OrderStatus.UNPAID).withDate(almostOutdated).withId(orderId)
                .build();

        // When
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        order.setDate(order.getDate().minusDays(1));
        orderService.saveOrder(order);
        Order retrievedOrder = orderService.getOrderById(order.getId()).get();

        assertEquals(order.getId(), retrievedOrder.getId());
        assertTrue(retrievedOrder.getOrderStatus().isUnpaid());
        assertTrue(order.getUnPaidOrderState().isToTransferToAuthorities());
        assertEquals(order.getUnPaidOrderState(), retrievedOrder.getUnPaidOrderState());
    }

}
