package com.foodOrder.controller;

import com.foodOrder.dto.RestaurantDto;
import com.foodOrder.model.Restaurant;
import com.foodOrder.model.User;
import com.foodOrder.request.CreateRestaurantRequest;
import com.foodOrder.service.IRestaurantService;
import com.foodOrder.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private IRestaurantService restaurantService;
    @Autowired
    private IUserService userService;

    @GetMapping("search")
    public ResponseEntity<List<Restaurant>> getAllRestaurantByKeyword(@RequestHeader("Authorization") String jwt,
                                                                      @RequestParam String keyword
    ) throws Exception {
        try {
            User user = userService.findUserByJwt(jwt);
            List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurant(@RequestHeader("Authorization") String jwt

    ) throws Exception {
        try {
            User user = userService.findUserByJwt(jwt);
            List<Restaurant> restaurant = restaurantService.getAllRestaurant();
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Restaurant>> getAllRestaurantById(@RequestHeader("Authorization") String jwt,
                                                                     @PathVariable Long id
    ) throws Exception {
        try {
            User user = userService.findUserByJwt(jwt);
            Optional<Restaurant> restaurant = restaurantService.findRestaurantById(id);
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}/add-favourite")
    public ResponseEntity<RestaurantDto> addToFavourite(@RequestHeader("Authorization") String jwt,
                                                                     @PathVariable Long id
    )throws Exception{
        try {
            User user = userService.findUserByJwt(jwt);
            RestaurantDto restaurant = restaurantService.addRestaurantToListFavouriteOfUser(id,user);
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
