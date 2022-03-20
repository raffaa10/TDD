package com.restaurant.exercice.model;

import java.time.LocalDateTime;

public abstract class Staff extends Person {
    private LocalDateTime startServiceAt;
    private LocalDateTime endServiceAt;
    private int workingTimeByDay = 7;

    public Staff() {

    }

    public Staff(String name, String firstName) {
        super(name, firstName);
        this.initializeStaffDate();
    }

    public void initializeStaffDate() {
        this.startServiceAt = LocalDateTime.now();
        this.endServiceAt = LocalDateTime.now().plusHours(workingTimeByDay);
    }

}
