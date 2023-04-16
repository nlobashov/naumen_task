package ru.nlobashov.naumen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nlobashov.naumen.dao.ProductDAO;
import ru.nlobashov.naumen.models.Product;
import ru.nlobashov.naumen.util.ErrorResponse;
import ru.nlobashov.naumen.util.ProductContainsErrorsException;
import ru.nlobashov.naumen.util.ProductNotFoundException;

import javax.validation.Valid;

@Controller
public class ProductsController {

    private final ProductDAO productDAO;

    @Autowired
    public ProductsController(ProductDAO productDAO)
    {
        this.productDAO = productDAO;
    }

    @GetMapping(value = {"/", "/products"})
    public String index(Model model)
    {
        model.addAttribute("products", productDAO.getListProducts());
        return "products/index";
    }

    @GetMapping("/products/{id}")
    public String showProductInfo(@PathVariable("id") int id, Model model) throws ProductNotFoundException {
        Product product = productDAO.getProduct(id);
        model.addAttribute("product", product);
        return "products/product_info";
    }

    @GetMapping("/products/filter")
    public String showProductsByFilter(@RequestParam("pattern") String pattern, Model model)
    {
        if (pattern.trim().isEmpty()) return "redirect:/products";
        model.addAttribute("products", productDAO.findBySubstring(pattern));
        return "products/index";
    }

    @GetMapping("/products/new")
    public String addProduct(@ModelAttribute("product") Product product, Model model)
    {
        model.addAttribute("page_title", "Новая позиция");
        return "products/form_new_product";
    }

    @PostMapping("/products")
    public String create(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "products/form_new_product";
        }
        productDAO.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) throws ProductNotFoundException {
        model.addAttribute("page_title", "Редактор позиции");
        model.addAttribute("product", productDAO.getProduct(id));
        return "products/edit";
    }

    @PutMapping("/products/{id}")
    public String update(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
                         @PathVariable("id") int id) throws ProductNotFoundException {
        if (bindingResult.hasErrors()) return "products/edit";
        productDAO.update(id, product);
        return "redirect:/products";
    }

    @DeleteMapping("/products/{id}")
    public String delete(@PathVariable("id") int id) {
        productDAO.delete(id);
        return "redirect:/products";
    }

    @ExceptionHandler
    @ResponseBody
    private ResponseEntity<ErrorResponse> handleException(ProductNotFoundException exception)
    {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseBody
    private ResponseEntity<ErrorResponse> handleException(ProductContainsErrorsException exception)
    {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}