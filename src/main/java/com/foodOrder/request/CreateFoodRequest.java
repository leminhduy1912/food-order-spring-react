package com.foodOrder.request;

import com.foodOrder.model.Category;
import com.foodOrder.model.IngredientsItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;

    private Category category;
    private List<String> images;
    private Long restaurantId;
    private boolean vagetarian;
    private boolean seasonal;
    private List<IngredientsItem> ingredients;
}
