package net.cmderobertis.productsandcategories.repositories;

import net.cmderobertis.productsandcategories.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
}
