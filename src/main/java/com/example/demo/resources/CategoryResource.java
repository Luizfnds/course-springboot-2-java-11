package com.example.demo.resources;

import com.example.demo.entities.Category;
import com.example.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> FindAll(){
        List<Category> list = service.FindAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = ("/{id}"))
    public ResponseEntity<Category> FindById(@PathVariable Long id){
        Category obj = service.FindById(id);
        return ResponseEntity.ok().body(obj);
    }
}
