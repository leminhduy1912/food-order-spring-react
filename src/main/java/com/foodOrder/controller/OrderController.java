package com.foodOrder.controller;

import com.foodOrder.model.Order;
import com.foodOrder.model.User;
import com.foodOrder.request.OrderRequest;
import com.foodOrder.service.IOrderService;
import com.foodOrder.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;
    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request,
                                             @RequestHeader("Authorization") String jwt) throws Exception{
        try {
            User user = userService.findUserByJwt(jwt);
            Order order = orderService.createOrder(request,jwt);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String jwt) throws Exception{
        try {
            User user = userService.findUserByJwt(jwt);
            List<Order> order = orderService.getUserOrder(jwt);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
