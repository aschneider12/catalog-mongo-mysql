package br.dev.as.catalog.service;

import br.dev.as.catalog.entities.mysql.Product;
import br.dev.as.catalog.repositories.mysql.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public ProductService() {
    }

    public void saveProduct(Product product) {

        repo.save(product);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public List<Product> getProductsByCategory(){
        return null;
    }
}
