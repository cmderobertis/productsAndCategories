package net.cmderobertis.productsandcategories.services;

import net.cmderobertis.productsandcategories.models.Category;
import net.cmderobertis.productsandcategories.models.Product;
import net.cmderobertis.productsandcategories.repositories.CategoryRepository;
import net.cmderobertis.productsandcategories.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repo;
    public ProductService(ProductRepository repo) {this.repo = repo;}
    // CREATE
    public void create(Product product) {repo.save(product);}
    // READ
    public List<Product> getAll() {return repo.findAll();}
    public Product getOne(Long id) {
        Optional<Product> license = repo.findById(id);
        return license.orElse(null);
    }
    // UPDATE
    public void update(Product product) {repo.save(product);}
    //DELETE
    public void delete(Long id) {repo.deleteById(id);}
    // ADD CATEGORY
    public void addCategory(Category category, Long id) {
        Product product = repo.findById(id).get();
        product.getCategories().add(category);
        repo.save(product);
    }
    // REMOVE CATEGORY
    public void removeCategory(Category category, Long id) {
        Product product = repo.findById(id).get();
        product.getCategories().remove(category);
        repo.save(product);
    }
}
