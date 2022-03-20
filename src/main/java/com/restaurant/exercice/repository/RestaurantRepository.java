package com.restaurant.exercice.repository;

import com.restaurant.exercice.model.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
