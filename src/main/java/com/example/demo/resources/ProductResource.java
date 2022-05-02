package com.example.demo.resources;

import com.example.demo.entities.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> FindAll(){
        List<Product> list = service.FindAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = ("{id}"))
    public ResponseEntity<Product> FindById(@PathVariable Long id){
        Product obj = service.FindById(id);
        return ResponseEntity.ok().body(obj);
    }
}
