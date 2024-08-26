package com.foodOrder.controller;

import com.foodOrder.model.IngredientCategory;
import com.foodOrder.model.IngredientsItem;
import com.foodOrder.request.CreateIngredientCategoryRequest;
import com.foodOrder.request.IngredientRequest;
import com.foodOrder.service.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
    @Autowired
    private IIngredientService ingredientService;

    @PostMapping("/category")
    private ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody CreateIngredientCategoryRequest request)
            throws Exception {
        try {
            IngredientCategory ingredientCategory = ingredientService.createIngredientCategory(request.getName(), request.getRestaurantId());
            return new ResponseEntity<>(ingredientCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping
    private ResponseEntity<IngredientsItem> createIngredientItem(
            @RequestBody IngredientRequest request)
            throws Exception {
        try {
            IngredientsItem ingredientsItem = ingredientService.createIngredientItem(request.getRestaurantId(), request.getName(), request.getCategoryId());
            return new ResponseEntity<>(ingredientsItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/stock")
    private ResponseEntity<IngredientsItem> updateStock(
            @PathVariable Long id)
            throws Exception {
        try {
            IngredientsItem ingredientsItem = ingredientService.updateStock(id);
            return new ResponseEntity<>(ingredientsItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{restaurant/{id}")
    private ResponseEntity<List<IngredientsItem>> getRestaurantIngredients(
            @PathVariable Long id)
            throws Exception {
        try {
            List<IngredientsItem> ingredientsItems = ingredientService.findRestaurantIngredient(id);
            return new ResponseEntity<>(ingredientsItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{restaurant/{id}/category")
    private ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
            @PathVariable Long id)
            throws Exception {
        try {
            List<IngredientCategory> ingredientsCategories = ingredientService.findIngredientCategoryByRestaurantId(id);
            return new ResponseEntity<>(ingredientsCategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
