package com.restaurant.exercice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Transient
    private Butler butler;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Waiter> waiters = new ArrayList<Waiter>();

    private int dailyIncomes;

    @Transient
    private List<Room> rooms = new ArrayList<Room>();

    private boolean isServiceStarted;

    public Restaurant() {

    }

    public Restaurant(List<Waiter> waiters) {
        this.dailyIncomes = 0;
        this.waiters = waiters;
    }

    public boolean isServiceStarted() {
        return isServiceStarted;
    }

    public void setServiceStarted(boolean isServiceStarted) {
        this.isServiceStarted = isServiceStarted;
    }

    public Butler getButler() {
        return butler;
    }

    public void setButler(Butler butler) {
        this.butler = butler;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Waiter> getWaiters() {
        return waiters;
    }

    public void setWaiters(List<Waiter> waiters) {
        this.waiters = waiters;
    }

    public int getDailyIncomes() {
        return dailyIncomes;
    }

    public void setDailyIncomes(int dailyIncomes) {
        this.dailyIncomes = dailyIncomes;
    }

    public void calculateAllDailyTotalPaidOrders() {
        this.dailyIncomes = waiters.stream()
                .map(e -> e.getDailyTotalPaidOrders())
                .reduce(0, (s, e) -> s + e);
    }

    public void startService() {
        this.butler.setOwnedRooms(rooms, this.isServiceStarted);
        this.isServiceStarted = true;
    }

    public void stopService() {
        for (Room room : this.rooms) {
            for (Table table : room.getTables()) {
                table.resetAssign();
            }
        }
        this.isServiceStarted = false;
        this.butler.setOwnedRooms(rooms, this.isServiceStarted);
    }

    public List<Order> retrieveOrdersToTransferToAuthorities() {
        List<Order> orders = new ArrayList<Order>();

        for (Waiter waiter : this.waiters) {
            for (Order order : waiter.getOrders()) {
                if (order.getUnPaidOrderState().isToTransferToAuthorities()) {
                    orders.add(order);
                }
            }
        }

        return orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
