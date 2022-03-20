package com.restaurant.utils.builder;

import java.util.List;

import com.restaurant.exercice.model.Room;
import com.restaurant.exercice.model.Table;
import com.restaurant.utils.generator.TableGenerator;

public class RoomBuilder {
    private Room room;

    public RoomBuilder() {
        this.room = new Room();
    }

    public RoomBuilder withNbOfTablesAndNbOfWaiterAndNbOfCustomers(int nbOfTables, int nbOfCustomers) {

        List<Table> tables = TableGenerator.stubsWithRoomAndWaiterAndCustomers(room, nbOfTables, nbOfCustomers);

        this.room.setTables(tables);
        return this;
    }

    public RoomBuilder withNbOfTables(int nbOfTables) {
        this.room.setTables(TableGenerator.stubs(nbOfTables));
        return this;
    }

    public RoomBuilder withTable(Table table) {
        this.room.addTable(table);
        return this;
    }

    public Room build() {
        return this.room;
    }

}
