package com.restaurant.exercice;

import com.restaurant.exercice.model.Restaurant;
import com.restaurant.utils.builder.RestaurantBuilder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Test du Restaurant")
public class RestaurantTest {
    // ÉTANT DONNÉ un restaurant ayant X serveurs
    // QUAND tous les serveurs prennent une commande d'un montant Y
    // ALORS le chiffre d'affaires du restaurant est X * Y
    // CAS(X = 0; X = 1; X = 2; X = 100)
    // CAS(Y = 1.0)
    @DisplayName("Test si le chiffre d'affaires du restaurant est égal à tous les chiffres d'affaires de tous les serveurs")
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 100 })
    public void testRestaurantDailyIncomesEqualsToAllWaitersDailyTotalPaidOrders(int nbWaiters) {
        // Given
        int nbOfOrders = 1;
        int orderPrice = 1;
        Restaurant restaurant = new RestaurantBuilder().withWaitersAndOrders(nbWaiters, nbOfOrders, orderPrice).build();
        int totalPrice = nbWaiters * orderPrice;

        // When
        restaurant.calculateAllDailyTotalPaidOrders();

        // Then
        Assertions.assertEquals(totalPrice, restaurant.getDailyIncomes());
    }

}
