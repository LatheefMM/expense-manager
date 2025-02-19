package com.example.expense_manager.Controller;

import com.example.expense_manager.contract.request.response.CategoryResponse;
import com.example.expense_manager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/v1/category")
@RequiredArgsConstructor


public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse>getCategories(){
        return  CategoryService.getCategories();
    }
}

