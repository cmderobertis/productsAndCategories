package net.cmderobertis.productsandcategories.controllers;

import net.cmderobertis.productsandcategories.models.Category;
import net.cmderobertis.productsandcategories.models.Product;
import net.cmderobertis.productsandcategories.services.ProductService;
import net.cmderobertis.productsandcategories.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {
    @Autowired
    ProductService service;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("products", service.getAll());
        model.addAttribute("categories", categoryService.getAll());
        return "index.jsp";
    }

    @GetMapping("/products/new")
    String createForm(Model model, @ModelAttribute("product") Product product) {
        return "createProduct.jsp";
    }

    @PostMapping("/products")
    String create(
            @Valid
            @ModelAttribute("product") Product product,
            BindingResult result) {
        if (result.hasErrors()) {
            return "createProduct.jsp";
        } else {
            service.create(product);
            return "redirect:/";
        }
    }

    @GetMapping("/products")
    String showAll(Model model) {
        List<Product> product = service.getAll();
        model.addAttribute("products", product);
        return "products.jsp";
    }
    @GetMapping("/products/{id}")
    String showOne(@PathVariable("id") Long id, Model model) {
        Product product = service.getOne(id);
        model.addAttribute("otherCategories", categoryService.getAll().stream().filter((category) -> !category.getProducts().contains(product)).collect(Collectors.toList()));
        model.addAttribute("product", product);
        return "showProduct.jsp";
    }
//    @GetMapping("/products/{id}/edit")
//    String updateForm(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("product", service.getOne(id));
//        model.addAttribute("persons", categoryService.getAll().stream().filter((person) -> person.getProducts() == null).collect(Collectors.toList()));
//        return "updateProduct.jsp";
//    }
//    @PutMapping("/products/{id}")
//    String update(
//            @Valid
//            @ModelAttribute("product") Product product,
//            BindingResult result) {
//        if (result.hasErrors()) {
//            return "updateProduct.jsp";
//        } else {
//            service.update(product);
//            return "redirect:/";
//        }
//    }
    @DeleteMapping("/products/{id}")
    String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/";
    }
    @PostMapping("/products/{id}/add")
    String add(@PathVariable("id") Long id, @RequestParam("categoryId") Long categoryId) {
        Category category = categoryService.getOne(categoryId);
        service.addCategory(category, id);
        return "redirect:/products/" + id;
    }
    @GetMapping("/products/{id}/remove/{categoryId}")
    String remove(@PathVariable("id") Long id, @PathVariable("categoryId") Long categoryId) {
        Category category = categoryService.getOne(categoryId);
        service.removeCategory(category, id);
        return "redirect:/products/" + id;
    }
}