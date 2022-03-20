package com.restaurant.exercice.service;

import com.restaurant.exercice.model.Restaurant;
import com.restaurant.exercice.repository.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant getRestaurantById(Integer id) {
        return restaurantRepository.findById(id).get();
    }

}
