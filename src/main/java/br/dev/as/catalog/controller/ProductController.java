package br.dev.as.catalog.controller;

import br.dev.as.catalog.entities.mysql.Product;
import br.dev.as.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;



    @PostMapping
    public ResponseEntity<Product> saveEvent(@RequestBody Product product) {
        service.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllEvents() {
        return ResponseEntity.ok(service.getAllProducts());
    }

}
