package br.dev.as.catalog.service;

import br.dev.as.catalog.entities.mongo.Comment;
import br.dev.as.catalog.entities.mysql.Product;
import br.dev.as.catalog.repositories.mongo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repo;

    public CommentService() {

    }

    public List<Comment> getAllCommentsForProduct(UUID product_id) {
        //TODO - find by producy
        return null;
    }
}
