package br.dev.as.catalog.entities.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("comment")
public class Comment {

    @Id
    private String id;

    private UUID productId;

    private String username;
    private String commentary;
    private Integer rating; /* 0 worst, 5 - best */

}
