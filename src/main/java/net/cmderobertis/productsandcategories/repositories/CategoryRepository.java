package net.cmderobertis.productsandcategories.repositories;

import net.cmderobertis.productsandcategories.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAll();
}