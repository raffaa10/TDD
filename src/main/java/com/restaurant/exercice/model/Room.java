package com.restaurant.exercice.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<Table> tables = new ArrayList<Table>();

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public void addTable(Table table) {
        this.tables.add(table);
    }

}
