package com.foodOrder.response;

import com.foodOrder.model.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String message;
    private USER_ROLE role;
    private String jwt;
}
