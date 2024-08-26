package com.foodOrder.controller;

import com.foodOrder.model.Restaurant;
import com.foodOrder.model.User;
import com.foodOrder.request.CreateRestaurantRequest;
import com.foodOrder.response.MessageResponse;
import com.foodOrder.service.IRestaurantService;
import com.foodOrder.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {
    @Autowired
    private IRestaurantService restaurantService;
    @Autowired
    private IUserService userService;
    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestHeader("Authorization") String jwt,
             @RequestBody CreateRestaurantRequest request)throws Exception{
        try {
            User user = userService.findUserByJwt(jwt);
            System.out.println("role"+user.getRole());
            Restaurant restaurant = restaurantService.createRestaurant(request,user);
            return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestHeader("Authorization") String jwt,
                                                       @RequestBody CreateRestaurantRequest request,
                                                       @PathVariable Long id)throws Exception{
        try {
            //User user = userService.findUserByJwt(jwt);
            Restaurant restaurant = restaurantService.updateRestaurant(request,id);
            return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(@RequestHeader("Authorization") String jwt,
                                                       @PathVariable Long id)throws Exception{
        try {
            //User user = userService.findUserByJwt(jwt);
            restaurantService.deleteRestaurant(id);
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setMessage("Restaurant delete successfully");
            return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateStatusRestaurant(@RequestHeader("Authorization") String jwt,
                                                            @PathVariable Long id)throws Exception{
        try {
            //User user = userService.findUserByJwt(jwt);
            Restaurant restaurant= restaurantService.updateRestaurantStatus(id);
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/find-by-userId")
    public ResponseEntity<Restaurant> findRestaurantByUserId(@RequestHeader("Authorization") String jwt
                                                             )throws Exception{
        try {
            User user = userService.findUserByJwt(jwt);
            Restaurant restaurant= restaurantService.findRestaurantByUserId(user.getId());
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
