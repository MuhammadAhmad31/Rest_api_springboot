package com.api.demo_rest.controller;

import com.api.demo_rest.models.entities.Product;
import com.api.demo_rest.services.ProductService;
import com.api.demo_rest.helpers.ResponseHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Product product) {
        try {
            Product savedProduct = productService.save(product);
            return ResponseEntity.ok(ResponseHelper.createResponse("success", savedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseHelper.createErrorResponse("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll() {
        try {
            Iterable<Product> products = productService.findAll();
            return ResponseEntity.ok(ResponseHelper.createResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseHelper.createErrorResponse("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findOne(@PathVariable("id") Long id) {
        try {
            Product product = productService.findOne(id);

            if (product != null) {
                return ResponseEntity.ok(ResponseHelper.createResponse("success", product));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ResponseHelper.createErrorResponse("error", "Product not found with ID: " + id));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseHelper.createErrorResponse("error", e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@RequestBody Product product) {
        try {
            Product updatedProduct = productService.save(product);
            return ResponseEntity.ok(ResponseHelper.createResponse("success", updatedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseHelper.createErrorResponse("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> removeOne(@PathVariable("id") Long id) {
        try {
            Product product = productService.findOne(id);

            if (product != null) {
                productService.removeOne(id);
                return ResponseEntity.ok(ResponseHelper.createResponse("success", null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ResponseHelper.createErrorResponse("error", "Product not found with ID: " + id));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseHelper.createErrorResponse("error", e.getMessage()));
        }
    }
}