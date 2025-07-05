package br.dev.as.catalog.repositories.mongo;

import br.dev.as.catalog.entities.mongo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*
Spring automatically discover this as an Mongo repository and create the relative connection.
*/
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
}
