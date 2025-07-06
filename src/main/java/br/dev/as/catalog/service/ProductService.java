package br.dev.as.catalog.service;

import br.dev.as.catalog.dto.CommentDTO;
import br.dev.as.catalog.dto.ProductDTO;
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

    @Autowired
    private CommentService commentService;

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

    public ProductDTO getProductResumeById(UUID productId) {

        List<CommentDTO> comments = commentService.getResumeComments(productId);

        double averageRating = comments.stream()
                .filter(c -> c.rating() != null) // ignora ratings nulos
                .mapToDouble(CommentDTO::rating)
                .average()
                .orElse(0.0); // retorna 0.0 se a lista estiver vazia ou sem ratings válidos

        Product product = getProductById(productId);



        return new ProductDTO(product.getName(),product.getDescription(),product.getPrice(), averageRating, comments);

    }

    public void deleteProduct(UUID id) {

        repo.deleteById(id);
    }


}
