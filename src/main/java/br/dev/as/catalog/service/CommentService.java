package br.dev.as.catalog.service;

import br.dev.as.catalog.dto.CommentDTO;
import br.dev.as.catalog.entities.mongo.Comment;
import br.dev.as.catalog.entities.mysql.Product;
import br.dev.as.catalog.repositories.mongo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.method.MethodValidationException;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repo;

    public CommentService() {

    }

    public List<Comment> getAllCommentsForProduct(UUID productId) {

        return repo.findCommentByProductId(productId);
    }

    public void newComment(Comment comment) {
        if(comment.getProduct() == null)
            throw new IllegalArgumentException("Product can't be null");

        comment.setProductId(comment.getProduct().getId());
        repo.save(comment);
    }

    //just for test the connection
    public List<Comment> getAllComments() {

        return repo.findAll();
    }

    public void deleteComment(String id) {

        repo.deleteById(id);
    }

    public Long getCountCommentForProduct(UUID productId) {

        return repo.countCommentByProductId(productId);
    }

    public Double getRatingAvgForProduct(UUID productId) {
        return repo.averageRatingForProduct(productId);
    }

    public List<CommentDTO> getResumeComments(UUID productId) {

        return repo.allCommentsForProduct(productId);
    }
}
