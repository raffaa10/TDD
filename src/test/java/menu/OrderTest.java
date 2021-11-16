package menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class OrderTest {

    /*
    @Test
    void testOrder() {

        Order sut = new Order("Plat1", 3.0);
        double orderSum = sut.getPrice();
        assertNotEquals(0.0, orderSum);

    }
     */

    @Test
    void testOrder1() {

        Order sut = new Order(new Plat( "Plat1", 7.0));

        double orderSum =  sut.getPlat().getPrice();

        assertNotEquals(0.0, orderSum);
    }

    @Test
    void testOrder2() {

        Order sut = new Order(new Plat( "Plat1", 7.0));

        double orderSum =  sut.getPlat().getPrice();

        assertEquals(7.0, orderSum);
    }


    @Test
    void testOrder3() {
        Order sut = new Order(new Plat( "Plat1", 7.0), 2);

        double orderSum = sut.getPlat().getPrice();

        assertEquals(14.0, orderSum);
    }





}
