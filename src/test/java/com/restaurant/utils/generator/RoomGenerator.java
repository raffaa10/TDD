package com.restaurant.utils.generator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.restaurant.exercice.model.Room;
import com.restaurant.utils.builder.RoomBuilder;

public class RoomGenerator {

    public static List<Room> stubsWithTables(int nbOfRooms, int nbOfTables) {
        return Stream.generate(RoomBuilder::new).limit(nbOfRooms).map(rb -> {
            rb.withNbOfTables(nbOfTables);
            return rb.build();
        }).collect(Collectors.toList());
    }

    public static List<Room> stubsWithTablesAndWaiterAndCustomers(int nbOfRooms, int nbOfTables, int nbOfCustomers) {
        return Stream.generate(RoomBuilder::new).limit(nbOfRooms).map(rb -> {
            rb.withNbOfTablesAndNbOfWaiterAndNbOfCustomers(nbOfTables, nbOfCustomers);
            return rb.build();
        }).collect(Collectors.toList());
    }
}
