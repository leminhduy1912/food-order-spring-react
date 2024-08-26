package com.foodOrder.service.impl;

import com.foodOrder.model.*;
import com.foodOrder.repository.*;
import com.foodOrder.request.OrderRequest;
import com.foodOrder.service.ICartService;
import com.foodOrder.service.IOrderService;
import com.foodOrder.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ICartService cartService;

    @Override
    public Order createOrder(OrderRequest orderRequest, String jwt) throws Exception {
        Address shipAddress = orderRequest.getAddress();
        User user = userService.findUserByJwt(jwt);
        Address savedAddress = addressRepository.save(shipAddress);
        if (!user.getAddresses().contains(savedAddress)) {
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }
        Optional<Restaurant> restaurant = restaurantRepository.findById(orderRequest.getRestaurantId());
        if (restaurant.isEmpty()) {
            throw new Exception("Restaurant not found");
        }
        Order createdOrder = new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setRestaurant(restaurant.get());
        createdOrder.setAddress(savedAddress);

        Cart cart = cartService.findCartByUserId(jwt);
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(item.getFood());
            orderItem.setIngredients(item.getIngredient());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setTotalPrice(item.getPrice());
            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }
        Long totalPrice = cart.getTotal();
        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(createdOrder);
        restaurant.get().getOrders().add(savedOrder);
        return createdOrder;
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new Exception("Order not found");
        }
        if (status.equals("OUT_FOR_DELIVERY") ||
                status.equals("DELIVERED") ||
                status.equals("COMPLETED") ||
                status.equals("PENDING")) {
            order.get().setOrderStatus(status);
            return orderRepository.save(order.get());
        } else {
            throw new Exception("Please select option status");
        }
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new Exception("Order not found");
        }
        orderRepository.delete(order.get());
    }

    @Override
    public List<Order> getUserOrder(String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return orderRepository.findByCustomerId(user.getId());
    }

    @Override
    public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orderList = orderRepository.findByRestaurantId(restaurantId);
        if (orderStatus!= null){
            orderList=orderList.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }
        return orderList;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new Exception("Order not found");
        }
        return order.get();
    }
}
