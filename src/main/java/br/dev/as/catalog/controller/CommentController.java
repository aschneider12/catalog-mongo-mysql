package br.dev.as.catalog.controller;

import br.dev.as.catalog.entities.mongo.Comment;
import br.dev.as.catalog.entities.mysql.Product;
import br.dev.as.catalog.service.CommentService;
import br.dev.as.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Comment> newComment(@RequestBody Comment comment) {
        service.newComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<Comment>> getAllCommentsOfProduct(@PathVariable UUID productId) {
        return ResponseEntity.ok(service.getAllCommentsForProduct(productId));
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok(service.getAllComments());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        service.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count/{productId}")
    public ResponseEntity<String> countTotalCommentsForProduct(@PathVariable UUID productId) {
        Product product = productService.getProductById(productId);

        Long totalCount = service.getCountCommentForProduct(productId);
        return ResponseEntity.ok("Total comments of '"+product.getName()
                +"': "+totalCount);
    }

    @GetMapping("/rating/{productId}")
    public ResponseEntity<String> ratingAvgForProduct(@PathVariable UUID productId) {

        Product product = productService.getProductById(productId);

        Double average = service.getRatingAvgForProduct(productId);

        return ResponseEntity.ok("AVG rating of '"+product.getName()
                +"': "+String.format("%.1f", average));
    }

}
