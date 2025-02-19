package com.example.expense_manager.service;


import com.example.expense_manager.constant.Color;
import com.example.expense_manager.model.Category;
import com.example.expense_manager.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CategoryService {
    private  final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public static List<CategoryResponse>getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(Category -> modelMapper.map
                (Category, CategoryResponse.class)).collect(Collectors.toList());
    }
    @PostConstruct
    public void initialzeValue(){
        saveCategory(Type.investment, Color.Lime);
        saveCategory(Type.savings,Color.blue);
        saveCategory(Type.expense,Color.red);
    }
    private  void saveCategory(Type type,Color color){
        Optional<Category>optionalCategory= CategoryRepository.findByType(type);
        if (!optionalCategory.isPresent()){
            Category category = Category.builder().type(type).color(color).build();
            CategoryRepository.save(category);
        }
    }
}


