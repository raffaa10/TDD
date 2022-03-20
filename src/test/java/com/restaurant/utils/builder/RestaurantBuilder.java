package com.restaurant.utils.builder;

import java.util.ArrayList;
import java.util.List;

import com.restaurant.exercice.model.Butler;
import com.restaurant.exercice.model.Restaurant;
import com.restaurant.exercice.model.Waiter;
import com.restaurant.utils.generator.RoomGenerator;
import com.restaurant.utils.generator.WaiterGenerator;

public class RestaurantBuilder {
    private Restaurant restaurant;

    public RestaurantBuilder() {
        List<Waiter> emptyList = new ArrayList<Waiter>();
        this.restaurant = new Restaurant(emptyList);
    }

    public RestaurantBuilder withNbOfWaiters(int nbOfWaiters) {
        this.withWaiters(WaiterGenerator.stubs(nbOfWaiters));
        return this;
    }

    public RestaurantBuilder withWaitersAndOrders(int nbOfWaiters, int nbOfOrders, int orderPrice) {
        this.withWaiters(WaiterGenerator.stubsWithPaidOrders(nbOfWaiters, nbOfOrders, orderPrice));
        return this;
    }

    public RestaurantBuilder withWaiters(List<Waiter> waiters) {
        this.restaurant.setWaiters(waiters);
        return this;
    }

    public RestaurantBuilder withDailyIncomes(int dailyIncomes) {
        this.restaurant.setDailyIncomes(dailyIncomes);
        return this;
    }

    public RestaurantBuilder withRoomsAndNbTablesAndNbWaitersAndNbCustomers(int nbOfRooms, int nbOfTables,
            int nbOfWaiters, int nbOfCustomers) {
        this.restaurant.setRooms(
                RoomGenerator.stubsWithTablesAndWaiterAndCustomers(nbOfRooms, nbOfTables, nbOfCustomers));
        return this;
    }

    public RestaurantBuilder withButler(Butler butler) {
        this.restaurant.setButler(butler);
        return this;
    }

    public RestaurantBuilder withNbOfRoomsAndNbOfTables(int nbOfRooms, int nbOfTables) {
        this.restaurant.setRooms(RoomGenerator.stubsWithTables(nbOfRooms, nbOfTables));
        return this;
    }

    public RestaurantBuilder withStartedService(boolean hasStarted) {
        this.restaurant.setServiceStarted(hasStarted);
        return this;
    }

    public Restaurant build() {
        return this.restaurant;
    }

}
