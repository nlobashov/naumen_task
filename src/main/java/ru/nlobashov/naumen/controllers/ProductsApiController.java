package ru.nlobashov.naumen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.nlobashov.naumen.dao.ProductDAO;
import ru.nlobashov.naumen.models.Product;
import ru.nlobashov.naumen.util.ErrorResponse;
import ru.nlobashov.naumen.util.ProductContainsErrorsException;
import ru.nlobashov.naumen.util.ProductNotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsApiController {

    private final ProductDAO productDAO;

    @Autowired
    public ProductsApiController(ProductDAO productDAO)
    {
        this.productDAO = productDAO;
    }

    @GetMapping()
    public List<Product> getListProducts()
    {
        return productDAO.getListProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) throws ProductNotFoundException
    {
        return productDAO.getProduct(id);
    }

    @GetMapping("/filter")
    public List<Product> showProductsByFilter(@RequestParam("pattern") String pattern, Model model)
    {
        return productDAO.findBySubstring(pattern);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Product product,
                                             BindingResult bindingResult) throws ProductContainsErrorsException
    {
        if (bindingResult.hasErrors())
            throw new ProductContainsErrorsException(getErrorsMessage(bindingResult));
        productDAO.save(product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid Product product,
                                             BindingResult bindingResult) throws ProductContainsErrorsException, ProductNotFoundException {
        if (bindingResult.hasErrors())
            throw new ProductContainsErrorsException(getErrorsMessage(bindingResult));
        productDAO.update(product.getId(),product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        productDAO.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(ProductNotFoundException exception)
    {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(ProductContainsErrorsException exception)
    {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private String getErrorsMessage(BindingResult bindingResult)
    {
        StringBuilder errorMessage = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors)
        {
            errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(";");
        }
        return errorMessage.toString();
    }
}
