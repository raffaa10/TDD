package com.restaurant.exercice.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.restaurant.exercice.enumeration.OrderStatus;
import com.restaurant.exercice.enumeration.UnPaidOrderState;

@Entity
@javax.persistence.Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int totalPrice;
    private UnPaidOrderState unPaidOrderState = UnPaidOrderState.NOTHING_TO_DECLARE;
    private LocalDateTime date;
    private OrderStatus orderStatus = OrderStatus.PENDING;

    @Transient
    private Table table;

    public Order() {

    }

    public Order(int totalPrice, Table table) {
        this.totalPrice = totalPrice;
        this.table = table;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public UnPaidOrderState getUnPaidOrderState() {
        return unPaidOrderState;
    }

    public void setUnPaidOrderState(UnPaidOrderState unPaidOrderState) {
        this.unPaidOrderState = unPaidOrderState;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;

        if (!this.isOrderLocked()) {
            if (orderStatus.equals(OrderStatus.UNPAID)) {
                this.unPaidOrderState = UnPaidOrderState.PINNED;
                this.setDate(this.date);
            } else {
                this.unPaidOrderState = UnPaidOrderState.NOTHING_TO_DECLARE;
            }
        }

    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setDate(LocalDateTime date) {

        if (Objects.nonNull(date)) {
            this.date = date;
            LocalDateTime now = LocalDateTime.now();
            long daysDifferences = ChronoUnit.DAYS.between(date, now);

            if (this.unPaidOrderState.isPinned() && daysDifferences >= 15) {
                this.unPaidOrderState = UnPaidOrderState.TO_TRANSFER_TO_AUTHORITIES;
            }
        }

    }

    public boolean isOrderLocked() {
        return this.unPaidOrderState.isTransferredToAuthorities();
    }

    public void tranferToAuthorities() {
        this.unPaidOrderState = UnPaidOrderState.TRANSFERRED_TO_AUTHORITIES;
    }

}
