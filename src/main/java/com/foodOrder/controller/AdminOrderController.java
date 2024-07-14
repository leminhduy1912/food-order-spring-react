package com.foodOrder.controller;

import com.foodOrder.model.Order;
import com.foodOrder.model.User;
import com.foodOrder.service.IOrderService;
import com.foodOrder.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;
    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String jwt,
                                                       @PathVariable Long restaurantId,
                                                       @RequestParam(required = false) String orderStatus
    ) throws Exception{
        try {
            User user = userService.findUserByJwt(jwt);
            List<Order> order = orderService.getRestaurantOrder(restaurantId,orderStatus);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/order/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(@RequestHeader("Authorization") String jwt,
                                                       @PathVariable Long orderId,
                                                       @RequestParam(required = false) String orderStatus
    ) throws Exception{
        try {
            User user = userService.findUserByJwt(jwt);
            Order order = orderService.updateOrderStatus(orderId,orderStatus);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
