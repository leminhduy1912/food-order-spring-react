package com.foodOrder.service;

import com.foodOrder.dto.RestaurantDto;
import com.foodOrder.model.Restaurant;
import com.foodOrder.model.User;
import com.foodOrder.request.CreateRestaurantRequest;

import java.util.List;
import java.util.Optional;

public interface IRestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user) throws Exception;
    public Restaurant updateRestaurant(CreateRestaurantRequest updateRequest, Long id) throws Exception;
    public void deleteRestaurant(Long id) throws Exception;
    public List<Restaurant> getAllRestaurant() throws Exception;
    public List<Restaurant> searchRestaurant(String keyword) throws Exception;
    public Optional<Restaurant> findRestaurantById(Long id) throws Exception;
    public Restaurant findRestaurantByUserId(Long userId) throws Exception;
    public RestaurantDto addRestaurantToListFavouriteOfUser(Long restaurantId,User user) throws Exception;
    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception;
}
