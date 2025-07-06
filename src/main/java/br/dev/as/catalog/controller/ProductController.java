package br.dev.as.catalog.controller;

import br.dev.as.catalog.dto.ProductDTO;
import br.dev.as.catalog.entities.mysql.Product;
import br.dev.as.catalog.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        service.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable UUID categoryId) {

        return ResponseEntity.ok(service.getProductsByCategory(categoryId));
    }

    @GetMapping("/find")
    public ResponseEntity<ProductDTO> getResumeProductById(
            @RequestParam(value = "id") UUID productId) {

        return ResponseEntity.ok(service.getProductResumeById(productId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
