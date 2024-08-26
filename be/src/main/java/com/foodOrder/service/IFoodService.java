package com.foodOrder.service;

import com.foodOrder.model.Category;
import com.foodOrder.model.Food;
import com.foodOrder.model.Restaurant;
import com.foodOrder.request.CreateFoodRequest;

import java.util.List;

public interface IFoodService {
    public Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant) throws Exception;
    public Food findFoodById(Long id) throws Exception;
    public List<Food> searchFood(String keyword) throws Exception;
    public Food updateStatusOfFood(Long id) throws Exception;
    public List<Food> getListFoodFromRestaurant(Long restaurantId,boolean isVegetarian
            ,boolean isSeasonal,boolean isNonVegetarian,String foodCategory) throws Exception;
    public void deleteFood(Long foodId) throws Exception;
}
