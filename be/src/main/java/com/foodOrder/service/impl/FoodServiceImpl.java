package com.foodOrder.service.impl;

import com.foodOrder.model.Category;
import com.foodOrder.model.Food;
import com.foodOrder.model.Restaurant;
import com.foodOrder.repository.FoodRepository;
import com.foodOrder.request.CreateFoodRequest;
import com.foodOrder.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements IFoodService {
    @Autowired
    private FoodRepository foodRepository;
    @Override
    public Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant) throws Exception {
        Food food = new Food();
        food.setDescription(request.getDescription());
        food.setName(request.getName());
        food.setFoodCategory(request.getCategory());
        food.setRestaurant(restaurant);
        food.setImages(request.getImages());
        food.setPrice(request.getPrice());
        food.setIngredientsItems(request.getIngredients());
        food.setSeasonal(request.isSeasonal());
        food.setVegetarian(request.isVagetarian());
        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);
        return savedFood;
    }

    @Override
    public Food findFoodById(Long id) throws Exception {
        Optional<Food> food = foodRepository.findById(id);
        if(food.isPresent()){
            return food.get();
        } else {
            throw new Exception("Food not found");
        }
    }

    @Override
    public List<Food> searchFood(String keyword) throws Exception {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food updateStatusOfFood(Long id) throws Exception {
        Optional<Food> food = foodRepository.findById(id);
        if(food.isEmpty()){
            throw new Exception("Food not found");
        }
        food.get().setAvailable(!food.get().isAvailable());
        return foodRepository.save(food.get());
    }

    @Override
    public List<Food> getListFoodFromRestaurant(Long restaurantId,
                                                boolean isVegetarian,
                                                boolean isSeasonal,
                                                boolean isNonVegetarian,
                                                String foodCategory) throws Exception {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
        if (isVegetarian){
            foods = filterByVegetarian(foods,isVegetarian);
        }
        if (isNonVegetarian){
            foods = filterByNonVegetarian(foods,isNonVegetarian);
        }
        if (isSeasonal){
            foods = filterBySeasonal(foods,isSeasonal);
        }
        if (foodCategory != null && foodCategory.equals("") ){
            foods = filterByCategory(foods,foodCategory);
        }
        return null;
    }
    private List<Food> filterByCategory(List<Food> foods,String category){
        return foods.stream().filter(food -> {
            if(food.getFoodCategory() != null){
                return food.getFoodCategory().getName().equals(category);
            }
            return false;
        }).collect(Collectors.toList());
    }
    private List<Food> filterByVegetarian(List<Food> foods,boolean isVegetarian){
        return foods.stream().filter(food -> food.isVegetarian() == isVegetarian).collect(Collectors.toList());
    }
    private List<Food> filterByNonVegetarian(List<Food> foods,boolean isNonVegetarian){
        return foods.stream().filter(food -> food.isVegetarian() == false).collect(Collectors.toList());
    }
    private List<Food> filterBySeasonal(List<Food> foods,boolean isSeasonal){
        return foods.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());
    }


    @Override
    public void deleteFood(Long foodId) throws Exception {
        Optional<Food> food = foodRepository.findById(foodId);
        if(food.isPresent()){
            food.get().setRestaurant(null);
            foodRepository.save(food.get());
        }
    }
}
