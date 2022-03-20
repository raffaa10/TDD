package com.restaurant.utils.generator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.restaurant.exercice.model.Restaurant;
import com.restaurant.utils.builder.RestaurantBuilder;

public class RestaurantGenerator {
    public static List<Restaurant> stubsWithRoomsAndTables(int nbOfRestaurants, int nbOfRooms, int nbOfTables) {
        return Stream.generate(RestaurantBuilder::new)
                .limit(nbOfRestaurants)
                .map(restaurant -> restaurant.withNbOfRoomsAndNbOfTables(nbOfRooms, nbOfTables).build())
                .collect(Collectors.toList());
    }

    public static List<Restaurant> stubsWithWaitersWithOrdersAndIncomes(int nbOfRestaurants, int nbOfWaiters,
            int nbOfOrders, int orderPrice) {

        return Stream.generate(RestaurantBuilder::new)
                .limit(nbOfRestaurants)
                .map(restaurant -> restaurant.withWaitersAndOrders(nbOfWaiters, nbOfOrders, orderPrice).build())
                .collect(Collectors.toList());
    }
}
