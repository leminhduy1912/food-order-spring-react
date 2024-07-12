package com.foodOrder.service.impl;

import com.foodOrder.model.Category;
import com.foodOrder.model.Restaurant;
import com.foodOrder.repository.CategoryRepository;
import com.foodOrder.service.ICategoryService;
import com.foodOrder.service.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private IRestaurantService restaurantService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Optional<Restaurant> restaurant = Optional.ofNullable(restaurantService.findRestaurantByUserId(userId));
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant.get());
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByUserIdAndRestaurantId(Long userId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantByUserId(userId);
        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Optional<Category> findCategoryById(Long id) throws Exception {
        return categoryRepository.findById(id);
    }
}
