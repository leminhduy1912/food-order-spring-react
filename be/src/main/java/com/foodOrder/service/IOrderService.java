package com.foodOrder.service;

import com.foodOrder.model.Order;
import com.foodOrder.model.User;
import com.foodOrder.request.OrderRequest;

import java.util.List;

public interface IOrderService {
    public Order createOrder(OrderRequest orderRequest, String jwt) throws Exception;
    public Order updateOrderStatus(Long orderId,String status) throws Exception;
    public void cancelOrder(Long orderId) throws Exception;
    public List<Order> getUserOrder(String jwt) throws Exception;
    public List<Order> getRestaurantOrder(Long restaurantId,String orderStatus) throws Exception;
    public Order findOrderById(Long orderId) throws Exception;
}
