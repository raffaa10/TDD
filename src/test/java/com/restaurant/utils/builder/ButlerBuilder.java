package com.restaurant.utils.builder;

import java.util.List;

import com.restaurant.exercice.model.Butler;
import com.restaurant.exercice.model.Room;
import com.restaurant.exercice.model.Waiter;

public class ButlerBuilder {
    private Butler butler;

    public ButlerBuilder() {
        this.butler = new Butler("", "");
    }

    public ButlerBuilder withFirstName(String firstName) {
        this.butler.setFirstName(firstName);
        return this;
    }

    public ButlerBuilder withLastName(String lastName) {
        this.butler.setName(lastName);
        return this;
    }

    public ButlerBuilder withOwnedRooms(List<Room> rooms) {
        this.butler.setOwnedRooms(rooms, false);
        return this;
    }

    public ButlerBuilder withWaiters(List<Waiter> waiters) {
        this.butler.setWaiters(waiters);
        return this;
    }

    public Butler build() {
        return this.butler;
    }
}
