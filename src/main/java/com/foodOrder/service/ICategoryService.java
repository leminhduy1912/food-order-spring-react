package com.foodOrder.service;

import com.foodOrder.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    public Category createCategory(String name,Long userId) throws Exception;
    public List<Category> findCategoryByUserIdAndRestaurantId(Long restaurantId) throws Exception;
    public Optional<Category> findCategoryById(Long id) throws Exception;
}
