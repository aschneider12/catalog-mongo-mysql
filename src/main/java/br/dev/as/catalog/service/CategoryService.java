package br.dev.as.catalog.service;

import br.dev.as.catalog.entities.mysql.Category;
import br.dev.as.catalog.entities.mysql.Product;
import br.dev.as.catalog.repositories.mysql.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public CategoryService() {

    }

    public void saveCategory(Category category) {

        repo.save(category);
    }

    public List<Category> getAllCategories() {

        return repo.findAll();
    }
}
