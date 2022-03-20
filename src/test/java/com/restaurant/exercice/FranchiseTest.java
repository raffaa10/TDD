package com.restaurant.exercice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.restaurant.exercice.model.Franchise;
import com.restaurant.utils.builder.FranchiseBuilder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Test de la franchise")
public class FranchiseTest {

    // ÉTANT DONNÉ une franchise ayant X restaurants de Y serveurs chacuns
    // QUAND tous les serveurs prennent une commande d'un montant Z
    // ALORS le chiffre d'affaires de la franchise est X * Y * Z
    // CAS(X = 0; X = 1; X = 2; X = 1000)
    // CAS(Y = 0; Y = 1; Y = 2; Y = 1000)
    // CAS(Z = 1.0)
    @ParameterizedTest
    @CsvSource(value = { "0,0,1", "1,1,1", "2,2,1", "1000,1000,1" })
    public void testFranchiseIncomesEqualsAllRestaurantsIncomes(int nbOfRestaurants, int nbOfWaiters, int ordersPrice) {
        // Given
        int nbOfOrders = 1;

        Franchise franchise = new FranchiseBuilder()
                .withRestaurantsWithWaitersWithOrdersAndIncomes(nbOfRestaurants, nbOfWaiters, nbOfOrders, ordersPrice)
                .build();

        int franchiseExpectedIncomes = ((ordersPrice * nbOfOrders) * nbOfWaiters) * nbOfRestaurants;

        // When
        franchise.calculateAllRestaurantsIncomes();

        // Then
        assertEquals(franchiseExpectedIncomes, franchise.getIncomes());
    }
}
