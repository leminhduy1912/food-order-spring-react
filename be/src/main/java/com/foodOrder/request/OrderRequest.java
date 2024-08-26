package com.foodOrder.request;

import com.foodOrder.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address address;
}
