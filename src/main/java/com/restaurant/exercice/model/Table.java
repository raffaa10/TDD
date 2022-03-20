package com.restaurant.exercice.model;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private Room currentRoom;
    private Staff assignedTo;
    private List<Customer> customers = new ArrayList<Customer>();

    public Staff getAssignedTo() {
        return assignedTo;
    }

    public boolean assignTo(Staff assignedTo, boolean isServiceStarted) {

        if (!isServiceStarted) {
            this.assignedTo = assignedTo;
            return true;
        }

        return false;
    }

    public void resetAssign() {
        this.assignedTo = null;
    }

    public void addCustomers(List<Customer> incomingCustomers) {
        customers.addAll(incomingCustomers);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

}
