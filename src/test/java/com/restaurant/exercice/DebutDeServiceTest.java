package com.restaurant.exercice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.restaurant.exercice.model.Butler;
import com.restaurant.exercice.model.Restaurant;
import com.restaurant.exercice.model.Room;
import com.restaurant.exercice.model.Table;
import com.restaurant.exercice.model.Waiter;
import com.restaurant.utils.builder.ButlerBuilder;
import com.restaurant.utils.builder.RestaurantBuilder;
import com.restaurant.utils.builder.WaiterBuilder;

import org.junit.jupiter.api.Test;

public class DebutDeServiceTest {
    // ÉTANT DONNE un restaurant ayant 3 tables
    // QUAND le service commence
    // ALORS elles sont toutes affectées au Maître d'Hôtel
    @Test
    public void testWhenServiceStartsAllTablesAreAssignedToButler() {
        // Given
        int nbOfTables = 3;
        Butler butler = new ButlerBuilder().withFirstName("ButlerFirstNameTest").withLastName("ButlerNameTest").build();
        Restaurant restaurant = new RestaurantBuilder().withNbOfRoomsAndNbOfTables(1, nbOfTables).withButler(butler)
                .build();

        // When
        restaurant.startService();

        // Then
        for (Room room : restaurant.getRooms()) {
            for (Table table : room.getTables()) {
                assertTrue(table.getAssignedTo().equals(butler));
            }
        }

    }

    // ÉTANT DONNÉ un restaurant ayant 3 tables dont une affectée à un serveur
    // QUAND le service débute
    // ALORS la table éditée est affectée au serveur et les deux autres au maître
    // d'hôtel
    @Test
    public void testWhenServiceStartThenTablesAreAssignedToButlerOrWaiter() {
        // Given
        int nbOfTables = 3;
        Butler butler = new ButlerBuilder().withFirstName("ButlerFirstNameTest").withLastName("ButlerNameTest").build();
        Waiter waiter = new WaiterBuilder().withFirstName("WaiterFirstName").withName("WaiterName").build();

        Restaurant restaurant = new RestaurantBuilder().withNbOfRoomsAndNbOfTables(1, nbOfTables).withButler(butler)
                .build();

        Table waiterOwnedTable = restaurant.getRooms().get(0).getTables().get(0);

        // When
        waiterOwnedTable.assignTo(waiter, false);
        restaurant.startService();

        // Then
        assertTrue(waiterOwnedTable.getAssignedTo().equals(waiter));

        for (Room room : restaurant.getRooms()) {
            for (Table table : room.getTables()) {
                if (table.equals(waiterOwnedTable))
                    continue;
                assertTrue(table.getAssignedTo().equals(butler));
            }
        }
    }

    // ÉTANT DONNÉ un restaurant ayant 3 tables dont une affectée à un serveur
    // QUAND le service débute
    // ALORS il n'est pas possible de modifier le serveur affecté à la table
    @Test
    public void testWhenServiceStartsThenTablesCannotBeAssigned() {
        // Given
        int nbOfTables = 3;
        Butler butler = new ButlerBuilder().withFirstName("ButlerFirstNameTest").withLastName("ButlerNameTest").build();
        Waiter waiter = new WaiterBuilder().withFirstName("WaiterFirstName").withName("WaiterName").build();
        Waiter waiterToAssignLater = new WaiterBuilder().withFirstName("WaiterToAssignLaterFirstName")
                .withName("WaiterToAssignLaterName").build();

        Restaurant restaurant = new RestaurantBuilder().withNbOfRoomsAndNbOfTables(1, nbOfTables).withButler(butler)
                .build();

        Table waiterOwnedTable = restaurant.getRooms().get(0).getTables().get(0);

        // When
        waiterOwnedTable.assignTo(waiter, false);
        restaurant.startService();

        // Then
        assertEquals(false, waiterOwnedTable.assignTo(waiterToAssignLater, restaurant.isServiceStarted()));
    }

    // ÉTANT DONNÉ un restaurant ayant 3 tables dont une affectée à un serveur
    // ET ayant débuté son service
    // QUAND le service se termine
    // ET qu'une table est affectée à un serveur
    // ALORS la table éditée est affectée au serveur et les deux autres au maître
    // d'hôtel
    @Test
    public void testWhenServiceStoppedAndTableIsAssignedToWaiterOtherTablesAreAssignedToButtler() {
        // Given
        int nbOfTables = 3;
        Butler butler = new ButlerBuilder().withFirstName("ButlerFirstNameTest").withLastName("ButlerNameTest").build();
        Waiter waiter = new WaiterBuilder().withFirstName("WaiterFirstName").withName("WaiterName").build();
        Restaurant restaurant = new RestaurantBuilder().withNbOfRoomsAndNbOfTables(1, nbOfTables).withButler(butler)
                .withStartedService(false)
                .build();

        restaurant.getRooms().get(0).getTables().get(0).assignTo(waiter, restaurant.isServiceStarted());
        restaurant.startService();

        Table tableToTest = restaurant.getRooms().get(0).getTables().get(1);

        // When
        restaurant.stopService();
        tableToTest.assignTo(waiter, restaurant.isServiceStarted());

        // Then
        assertEquals(waiter, tableToTest.getAssignedTo());

        for (Room room : restaurant.getRooms()) {
            for (Table table : room.getTables()) {
                if (table.equals(tableToTest))
                    continue;

                assertEquals(butler, table.getAssignedTo());
            }
        }

    }

}
