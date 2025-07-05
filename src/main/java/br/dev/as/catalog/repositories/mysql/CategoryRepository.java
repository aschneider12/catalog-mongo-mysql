package br.dev.as.catalog.repositories.mysql;

import br.dev.as.catalog.entities.mysql.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

}