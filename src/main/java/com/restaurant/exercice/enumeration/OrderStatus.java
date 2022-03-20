package com.restaurant.exercice.enumeration;

public enum OrderStatus {
    PAID,
    UNPAID,
    PENDING;

    public boolean isPaid() {
        return this.equals(PAID);
    }

    public boolean isUnpaid() {
        return this.equals(UNPAID);
    }
}
