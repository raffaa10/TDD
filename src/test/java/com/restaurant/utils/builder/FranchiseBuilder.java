package com.restaurant.utils.builder;

import java.util.List;

import com.restaurant.exercice.model.Franchise;
import com.restaurant.exercice.model.Restaurant;
import com.restaurant.utils.generator.RestaurantGenerator;

public class FranchiseBuilder {
    private Franchise franchise;

    public FranchiseBuilder() {
        franchise = new Franchise();
    }

    public FranchiseBuilder withRestaurantsWithWaitersWithOrdersAndIncomes(int nbOfRestaurants, int nbOfWaiters,
            int nbOfOrders, int orderPrice) {

        this.withRestaurants(RestaurantGenerator.stubsWithWaitersWithOrdersAndIncomes(nbOfRestaurants, nbOfWaiters,
                nbOfOrders, orderPrice));

        return this;
    }

    public FranchiseBuilder withRestaurants(List<Restaurant> restaurants) {
        this.franchise.setRestaurants(restaurants);
        return this;
    }

    public Franchise build() {
        return this.franchise;
    }
}
