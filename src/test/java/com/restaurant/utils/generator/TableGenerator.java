package com.restaurant.utils.generator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.restaurant.exercice.model.Customer;
import com.restaurant.exercice.model.Room;
import com.restaurant.exercice.model.Table;
import com.restaurant.exercice.model.Waiter;
import com.restaurant.utils.builder.TableBuilder;
import com.restaurant.utils.builder.WaiterBuilder;

public class TableGenerator {

    public static List<Table> stubs(int nbOfTables) {
        return Stream.generate(TableBuilder::new).limit(nbOfTables).map(tb -> {
            return tb.build();
        }).collect(Collectors.toList());
    }

    public static List<Table> stubsWithRoomAndWaiterAndCustomers(Room room, int nbOfTables, int nbOfCustomers) {

        List<Table> tables = Stream.generate(TableBuilder::new).limit(nbOfTables).map(tb -> {
            // Méthode appelée à chaque table pour ne pas avoir de serveurs et de clients
            // avec les mêmes références mémoire
            Waiter waiter = new WaiterBuilder().withFirstName("WaiterFirstName").withName("WaiterName").build();
            List<Customer> customers = CustomerGenerator.stubs(nbOfCustomers);

            tb.withRoom(room);
            tb.withAssignedTo(waiter);
            tb.withCustomers(customers);

            return tb.build();
        }).collect(Collectors.toList());

        return tables;
    }

}
