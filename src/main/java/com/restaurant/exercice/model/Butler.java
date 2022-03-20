package com.restaurant.exercice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Butler extends Staff {

    private List<Room> ownedRooms = new ArrayList<Room>();
    private List<Waiter> waiters = new ArrayList<Waiter>();

    public Butler(String name, String firstName) {
        super(name, firstName);
    }

    public List<Room> getOwnedRooms() {
        return ownedRooms;
    }

    public void setOwnedRooms(List<Room> ownedRooms, boolean isServiceStarted) {
        for (Room room : ownedRooms) {
            for (Table table : room.getTables()) {
                if (Objects.isNull(table.getAssignedTo())) {
                    table.assignTo(this, isServiceStarted);
                }
            }
        }

        this.ownedRooms = ownedRooms;
    }

    public List<Waiter> getWaiters() {
        return waiters;
    }

    public void setWaiters(List<Waiter> waiters) {
        this.waiters = waiters;
    }

}
