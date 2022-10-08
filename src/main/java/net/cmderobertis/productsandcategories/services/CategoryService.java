package net.cmderobertis.productsandcategories.services;

import net.cmderobertis.productsandcategories.models.Category;
import net.cmderobertis.productsandcategories.models.Product;
import net.cmderobertis.productsandcategories.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repo;
    CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }
    // CREATE
    public void create(Category category) {
        repo.save(category);
    }
    // READ
    public List<Category> getAll() {
        return repo.findAll();
    }
    public Category getOne(Long id) {
        Optional<Category> person = repo.findById(id);
        return person.orElse(null);
    }
    // UPDATE
    public void update(Category category) {
        repo.save(category);
    }
    //DELETE
    public void delete(Long id) {
        repo.deleteById(id);
    }
    public void addProduct(Product product, Long id) {
        Category category = repo.findById(id).get();
        category.getProducts().add(product);
        repo.save(category);
    }
    public void removeProduct(Product product, Long id) {
        Category category = repo.findById(id).get();
        category.getProducts().remove(product);
        repo.save(category);
    }
}
