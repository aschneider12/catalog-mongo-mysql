package br.dev.as.catalog.dto;

import br.dev.as.catalog.entities.mongo.Comment;

import java.util.List;

public record ProductDTO(String name, String description, Double price,
                         Double rating, List<CommentDTO> commentaries) {
}
