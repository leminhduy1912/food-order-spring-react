package com.foodOrder.request;

import com.foodOrder.model.Address;
import com.foodOrder.model.ContactInfomation;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInfomation contactInfomation;
    private String openingHour;
    private List<String> images;
    private LocalDateTime registrationDate;
}
