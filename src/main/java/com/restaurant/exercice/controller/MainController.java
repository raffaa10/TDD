package com.restaurant.exercice.controller;

import java.util.Optional;

import com.restaurant.exercice.model.Order;
import com.restaurant.exercice.model.Restaurant;
import com.restaurant.exercice.service.OrderService;
import com.restaurant.exercice.service.RestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class MainController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<String> welcomePage(){
        return ResponseEntity.ok().body("Only orders are testable in this application please go to <strong>/order/{id}</strong>");
    }

    @GetMapping("/order/{id}")
    public ResponseEntity getOrder(@PathVariable Integer id) {
        Optional<Order> order = orderService.getOrderById(id);

        if(order.isPresent()){
            return ResponseEntity.ok().body(orderService.getOrderById(id).get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This order does not exist");
        }
        
    }

    @PutMapping("/order/{id}/pay")
    public ResponseEntity<String> payForOrder(@PathVariable Integer id) {
        orderService.payOrder(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Integer id) {
        Restaurant restaurant = this.restaurantService.getRestaurantById(id);
        return ResponseEntity.ok().body(restaurant);
    }
}
