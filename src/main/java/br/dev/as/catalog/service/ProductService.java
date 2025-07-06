package br.dev.as.catalog.service;

import br.dev.as.catalog.entities.mysql.Category;
import br.dev.as.catalog.entities.mysql.Product;
import br.dev.as.catalog.repositories.mysql.CategoryRepository;
import br.dev.as.catalog.repositories.mysql.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private CategoryRepository categoryRepo;

    public ProductService() {
    }

    public void saveProduct(Product product) {

        repo.save(product);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public List<Product> getProductsByCategory(UUID categoryId){
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada!"));

        return repo.findProductsByCategories(category);
    }

    public Product getProductById(UUID productId) {

       return repo.findById(productId)
               .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado!"));
    }

    public void deleteProduct(UUID id) {

        repo.deleteById(id);
    }
}
