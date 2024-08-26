package com.foodOrder.service;

import com.foodOrder.model.Cart;
import com.foodOrder.model.CartItem;
import com.foodOrder.request.AddCartItemRequest;

public interface ICartService {
    public CartItem addItemToCart(AddCartItemRequest request,String jwt) throws Exception;
    public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws Exception;
    public Cart removeItemFromCart(Long cartItemId,String jwt) throws Exception;
    public Long calculateCartItemTotals(Cart cart) throws Exception;
    public Cart findCartById(Long id) throws Exception;
    public Cart findCartByUserId(String jwt) throws Exception;
    public Cart clearCart(String jwt) throws Exception;
}
