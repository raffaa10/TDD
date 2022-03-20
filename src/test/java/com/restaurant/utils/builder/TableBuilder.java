package com.restaurant.utils.builder;

import java.util.List;

import com.restaurant.exercice.model.Customer;
import com.restaurant.exercice.model.Room;
import com.restaurant.exercice.model.Staff;
import com.restaurant.exercice.model.Table;

public class TableBuilder {
    private Table table;

    public TableBuilder() {
        this.table = new Table();
    }

    public TableBuilder withRoom(Room room) {
        this.table.setCurrentRoom(room);
        return this;
    }

    public TableBuilder withCustomers(List<Customer> customers) {
        this.table.setCustomers(customers);
        return this;
    }

    public TableBuilder withAssignedTo(Staff staff) {
        this.table.assignTo(staff, false);
        return this;
    }

    public Table build() {
        return this.table;
    }

}
