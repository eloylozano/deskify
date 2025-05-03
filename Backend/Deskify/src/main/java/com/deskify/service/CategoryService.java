package com.deskify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deskify.model.Category;
import com.deskify.model.Status;
import com.deskify.repository.CategoryRepository;
import com.deskify.service.interfaces.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepo;

    @Override
    public List<Category> getAllCategories() {
        List<Category> list = categoryRepo.findAll();
        return list;
    }

}
