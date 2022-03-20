package com.restaurant.exercice.model;

import java.util.ArrayList;
import java.util.List;

public class Franchise {
    private List<Restaurant> restaurants;
    private int incomes;

    public Franchise() {
        this.restaurants = new ArrayList<Restaurant>();
        this.incomes = 0;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public int getIncomes() {
        return this.incomes;
    }

    public void setIncomes(int incomes) {
        this.incomes = incomes;
    }

    public void calculateAllRestaurantsIncomes() {
        this.incomes = restaurants.stream()
                .mapToInt(restaurant -> {
                    restaurant.calculateAllDailyTotalPaidOrders();
                    return restaurant.getDailyIncomes();
                })
                .reduce(0, (total, currVal) -> total + currVal);
    }

}
