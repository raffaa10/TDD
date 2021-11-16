package menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class OrderTest {

    
    @Test
    void testOrder() {

        Order sut = new Order("Plat1", 3.0);
        double orderSum = sut.getPrice();
        assertNotEquals(0.0, orderSum);

    }






}
