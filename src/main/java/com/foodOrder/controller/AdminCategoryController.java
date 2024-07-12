package com.foodOrder.controller;

import com.foodOrder.model.Category;
import com.foodOrder.model.User;
import com.foodOrder.service.ICategoryService;
import com.foodOrder.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/category")
public class AdminCategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category
            , @RequestHeader("Authorization") String jwt) throws Exception {
        try {
            User user = userService.findUserByJwt(jwt);
            Category createCategory = categoryService.createCategory(category.getName(), user.getId());
            return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/restaurant/{id}")
    public ResponseEntity<List<Category>> getCategoryByResraurantId(
            @RequestHeader("Authorization") String jwt) throws Exception {
        try {
            User user = userService.findUserByJwt(jwt);
            List<Category> categories = categoryService.findCategoryByUserIdAndRestaurantId(user.getId());
            return new ResponseEntity<>(categories, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
