package com.foodOrder.controller;

import com.foodOrder.model.Food;
import com.foodOrder.model.Restaurant;
import com.foodOrder.model.User;
import com.foodOrder.request.CreateFoodRequest;
import com.foodOrder.response.MessageResponse;
import com.foodOrder.service.IFoodService;
import com.foodOrder.service.IRestaurantService;
import com.foodOrder.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private IFoodService foodService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRestaurantService restaurantService;
    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest request,
                                           @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwt(jwt);
        Optional<Restaurant> restaurant = restaurantService.findRestaurantById(request.getRestaurantId());
        Food food = foodService.createFood(request,request.getCategory(),restaurant.get());
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt) throws Exception{

        try {
            User user = userService.findUserByJwt(jwt);
            restaurantService.deleteRestaurant(id);
            MessageResponse response = new MessageResponse();
            response.setMessage("Delete food successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            MessageResponse response = new MessageResponse();
            response.setMessage("Internal server error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateFood(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt)
            throws Exception{
        try {
            User user = userService.findUserByJwt(jwt);
            Food food = foodService.updateStatusOfFood(id);
            MessageResponse response = new MessageResponse();
            response.setMessage("Update status food successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            MessageResponse response = new MessageResponse();
            response.setMessage("Internal server error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
