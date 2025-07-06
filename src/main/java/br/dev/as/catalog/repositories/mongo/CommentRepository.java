package br.dev.as.catalog.repositories.mongo;

import br.dev.as.catalog.entities.mongo.Comment;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/*
Spring automatically discover this as an Mongo repository and create the relative connection.
*/
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findCommentByProductId(UUID productId);

    long countCommentByProductId(UUID productId);

    @Aggregation(pipeline = {
            "{ '$match': { 'productId': ?0 } }",
            "{ '$group': { '_id': null, 'averageRating': { '$avg': '$rating' } } }",
            "{ '$project': { '_id': 0, 'averageRating': 1 } }"
    })
    double averageRatingForProduct(UUID productId);
}
