package net.cmderobertis.productsandcategories.controllers;
import net.cmderobertis.productsandcategories.models.Category;
import net.cmderobertis.productsandcategories.models.Product;
import net.cmderobertis.productsandcategories.services.CategoryService;
import net.cmderobertis.productsandcategories.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CategoryController {
    @Autowired
    CategoryService service;
    @Autowired
    ProductService productService;
    @GetMapping("/categories/new")
    String createForm(Model model, @ModelAttribute("category") Category category) {
        model.addAttribute("products", productService.getAll().stream().filter((product) -> !product.getCategories().contains(category)).collect(Collectors.toList()));
        return "createCategory.jsp";
    }
    @PostMapping("/categories")
    String create(@ModelAttribute("category") Category category) {
        service.create(category);
        return "redirect:/";
    }
    @GetMapping("/categories")
    String showAll(Model model) {
        List<Category> categories = service.getAll();
        model.addAttribute("categories", categories);
        return "categories.jsp";
    }
    @GetMapping("/categories/{id}")
    String showOne(@PathVariable("id") Long id, Model model) {
        Category category = service.getOne(id);
        model.addAttribute("otherProducts", productService.getAll().stream().filter((product) -> !product.getCategories().contains(category)).collect(Collectors.toList()));
        model.addAttribute("category", category);
        return "showCategory.jsp";
    }
    @GetMapping("/categories/{id}/edit")
    String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("category", service.getOne(id));
        return "updateCategory.jsp";
    }
    @PutMapping("/categories/{id}")
    String update(
            @Valid
            @ModelAttribute("category") Category category,
            BindingResult result) {
        if (result.hasErrors()) {
            return "updateCategory.jsp";
        } else {
            service.update(category);
            return "redirect:/";
        }
    }
    @DeleteMapping("/categories/{id}")
    String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/";
    }
    @PostMapping("/categories/{id}/add")
    String add(@PathVariable("id") Long id, @RequestParam("productId") Long productId) {
        Product product = productService.getOne(productId);
        service.addProduct(product, id);
        return "redirect:/categories/" + id;
    }
    @GetMapping("/categories/{id}/remove/{productId}")
    String remove(@PathVariable("id") Long id, @PathVariable("productId") Long productId) {
        Product product = productService.getOne(productId);
        service.removeProduct(product, id);
        return "redirect:/categories/" + id;
    }
}
