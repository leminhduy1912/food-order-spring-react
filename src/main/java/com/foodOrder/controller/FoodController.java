package com.foodOrder.controller;

import com.foodOrder.model.Food;
import com.foodOrder.model.Restaurant;
import com.foodOrder.model.User;
import com.foodOrder.request.CreateFoodRequest;
import com.foodOrder.service.IFoodService;
import com.foodOrder.service.IRestaurantService;
import com.foodOrder.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private IFoodService foodService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String keyword,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<Food> food = foodService.searchFood(keyword);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@PathVariable Long restaurantId,
                                                        @RequestParam boolean vegetarian,
                                                        @RequestParam boolean nonVegetarian,
                                                        @RequestParam boolean seasonal,
                                                        @RequestParam(required = false) String food_category,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<Food> food = foodService.getListFoodFromRestaurant(restaurantId,vegetarian,nonVegetarian,seasonal,food_category);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
