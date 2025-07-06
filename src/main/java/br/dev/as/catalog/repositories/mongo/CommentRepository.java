package br.dev.as.catalog.repositories.mongo;

import br.dev.as.catalog.dto.CommentDTO;
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

    /*
        Projetar o DTO apenas com os atributos principais.
        {
          $project: {
            _id: 0,                  // exclui o _id (por default sempre é retornado)
            username: 1,             // mantém o campo username (1 indica que deve retornar)
            comment: "$commentary",  // renomeia commentary para comment
            rating: 1                // mantém rating
          }
        }
     */
    @Aggregation(pipeline = {
            "{ '$match': { 'productId': ?0 } }",
            "{ '$project': { '_id': 0, 'username': 1, 'comment': '$commentary', 'rating': 1 } }"
    })
    List<CommentDTO> allCommentsForProduct(UUID productId);

}
