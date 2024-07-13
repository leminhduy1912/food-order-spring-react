package com.foodOrder.service.impl;

import com.foodOrder.model.Category;
import com.foodOrder.model.IngredientCategory;
import com.foodOrder.model.IngredientsItem;
import com.foodOrder.model.Restaurant;
import com.foodOrder.repository.IngredientCategoryRepository;
import com.foodOrder.repository.IngredientItemRepository;
import com.foodOrder.service.IIngredientService;
import com.foodOrder.service.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IIngredientService {
    @Autowired
    private IngredientItemRepository ingredientItemRepository;
    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;
    @Autowired
    private IRestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

        Optional<Restaurant> restaurant = restaurantService.findRestaurantById(restaurantId);
        if (restaurant.isEmpty()) {
            throw new Exception("Restaurant not found");
        }
        IngredientCategory category = new IngredientCategory();
        category.setRestaurant(restaurant.get());
        category.setName(name);
        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);
        if (opt.isEmpty()) {
            throw new Exception("Ingredient category not found");
        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception {
        Optional<Restaurant> restaurant = restaurantService.findRestaurantById(restaurantId);
        if (restaurant.isEmpty()) {
            throw new Exception("Restaurant not found");
        }
        return ingredientCategoryRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredient(Long restaurantId) throws Exception {
        Optional<Restaurant> restaurant = restaurantService.findRestaurantById(restaurantId);
        if (restaurant.isEmpty()) {
            throw new Exception("Restaurant not found");
        }
        return ingredientItemRepository.findByRestaurantId(restaurant.get().getId());
    }


    @Override
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Optional<Restaurant> restaurant = restaurantService.findRestaurantById(restaurantId);
        Optional<IngredientCategory> category = ingredientCategoryRepository.findById(categoryId);
        if (restaurant.isEmpty()) {
            throw new Exception("Restaurant not found");
        }
        if (category.isEmpty()) {
            throw new Exception("Category not found");
        }
        IngredientsItem ingredientsItem = new IngredientsItem();
        ingredientsItem.setRestaurant(restaurant.get());
        ingredientsItem.setName(ingredientName);
        ingredientsItem.setCategory(category.get());

        category.get().getIngredients().add(ingredientsItem);
        return ingredientItemRepository.save(ingredientsItem);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> ingredientsItem = ingredientItemRepository.findById(id);
        if (ingredientsItem.isEmpty()){
            throw new Exception("Ingredient item not found");
        }
        IngredientsItem update = ingredientsItem.get();
        update.setInStock(!ingredientsItem.get().isInStock());
        return ingredientItemRepository.save(update);
    }
}
