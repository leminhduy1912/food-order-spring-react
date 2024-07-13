package com.foodOrder.controller;

import com.foodOrder.model.Cart;
import com.foodOrder.model.CartItem;
import com.foodOrder.model.User;
import com.foodOrder.request.AddCartItemRequest;

import com.foodOrder.request.UpdateCartItemRequest;
import com.foodOrder.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private ICartService cartService;
    @PostMapping("/api/cart")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest request
            , @RequestHeader("Authorization") String jwt){
        try {
              CartItem cartItem = cartService.addItemToCart(request,jwt);
              return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/cart-item/update-quantity")
    public ResponseEntity<CartItem> updateCartItem(@RequestBody UpdateCartItemRequest request
            , @RequestHeader("Authorization") String jwt){
        try {
            CartItem cartItem = cartService.updateCartItemQuantity(request.getCartItemId(), request.getQuantity());
            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCartItemFromCart(@PathVariable Long id
            , @RequestHeader("Authorization") String jwt){
        try {
            Cart cart = cartService.removeItemFromCart(id,jwt);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(
            @RequestHeader("Authorization") String jwt){
        try {
            Cart cart = cartService.clearCart(jwt);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(
            @RequestHeader("Authorization") String jwt){
        try {
            Cart cart = cartService.findCartByUserId(jwt);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
