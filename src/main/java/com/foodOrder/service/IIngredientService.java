package com.foodOrder.service;

import com.foodOrder.model.IngredientCategory;
import com.foodOrder.model.IngredientsItem;

import java.util.List;

public interface IIngredientService {
    public IngredientCategory createIngredientCategory(String name,Long restaurantId) throws Exception;
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception;
    public List<IngredientsItem> findRestaurantIngredient(Long restaurantId) throws Exception;
    public IngredientsItem createIngredientItem(Long restaurantId,String ingredientName,Long categoryId) throws Exception;
    public IngredientsItem updateStock(Long id) throws Exception;
}
