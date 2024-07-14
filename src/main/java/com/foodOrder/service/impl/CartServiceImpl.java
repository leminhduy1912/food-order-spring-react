package com.foodOrder.service.impl;

import com.foodOrder.model.Cart;
import com.foodOrder.model.CartItem;
import com.foodOrder.model.Food;
import com.foodOrder.model.User;
import com.foodOrder.repository.CartItemRepository;
import com.foodOrder.repository.CartRepository;
import com.foodOrder.repository.FoodRepository;
import com.foodOrder.request.AddCartItemRequest;
import com.foodOrder.service.ICartService;
import com.foodOrder.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Override
    public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Optional<Food> food = foodRepository.findById(request.getFoodId());
        if (food.isEmpty()){
            throw new Exception("Food not found");
        }
        Cart cart = cartRepository.findByCustomerId(user.getId());
        for(CartItem item : cart.getCartItems()){
            if(item.getFood().equals(food)){
                int newQuantity = item.getQuantity()+ request.getQuantity();
                return updateCartItemQuantity(item.getId(),newQuantity);
            }
        }
        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food.get());
        newCartItem.setCart(cart);
        newCartItem.setQuantity(request.getQuantity());
        newCartItem.setIngredient(request.getIngredients());
        newCartItem.setPrice(request.getQuantity()*food.get().getPrice());

        CartItem savedCartItem = cartItemRepository.save(newCartItem);
        cart.getCartItems().add(savedCartItem);
        return cartItemRepository.save(savedCartItem);
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if (cartItem.isEmpty()){
            throw new Exception("Cart item not found");
        }
        CartItem update = cartItem.get();
        update.setQuantity(quantity);
        update.setPrice(cartItem.get().getPrice()*quantity);
        return cartItemRepository.save(update);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if (cartItem.isEmpty()){
            throw new Exception("Cart item not found");
        }
        CartItem removeItem = cartItem.get();
        cart.getCartItems().remove(removeItem);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartItemTotals(Cart cart) throws Exception {
        Long totalPrice = 0L;
        for(CartItem item :cart.getCartItems()){
            totalPrice+= item.getFood().getPrice()*item.getQuantity();
        }
        return totalPrice;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isEmpty()){
            throw new Exception("Cart not found");
        }
        return cart.get();
    }

    @Override
    public Cart findCartByUserId(String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        if(cart==null){
            throw new Exception("Cart not found");
        }
        cart.setTotal(calculateCartItemTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }
}
