package com.api.demo_rest.controller;

import com.api.demo_rest.models.entities.Product;
import com.api.demo_rest.services.ProductService;
import com.api.demo_rest.helpers.ResponseHelper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProduct(@Valid @RequestBody Product product, Errors errors) {
        try {
            if (errors.hasErrors()) {
                return ResponseEntity.badRequest().body(ResponseHelper.ValidationErrorResponse(errors));
            }
            Product savedProduct = productService.save(product);
            return ResponseEntity.ok(ResponseHelper.createResponse("Data berhasil ditambahkan", savedProduct, HttpStatus.OK.value(), "success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseHelper.createErrorResponse("Data tidak dapat ditambahkan", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "error"));
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll() {
        try {
            Iterable<Product> products = productService.findAll();
            return ResponseEntity.ok(ResponseHelper.createResponse("Data Ditampilkan", products, HttpStatus.OK.value(), "success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseHelper.createErrorResponse("Data tidak dapat ditampilkan", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "error"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findOne(@PathVariable("id") Long id) {
        try {
            Product product = productService.findOne(id);

            if (product != null) {
                return ResponseEntity.ok(ResponseHelper.createResponse("Data ditampilkan", product, HttpStatus.OK.value(), "success"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ResponseHelper.createErrorResponse("Data tidak ada", "Product not found with ID: " + id, HttpStatus.NOT_FOUND.value(), "error"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseHelper.createErrorResponse("Data tidak dapat ditampilkan", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "error"));
        }
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@RequestBody Product product) {
        try {
            Product updatedProduct = productService.save(product);
            return ResponseEntity.ok(ResponseHelper.createResponse("Data berhasil di update", updatedProduct, HttpStatus.OK.value(), "success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseHelper.createErrorResponse("Data tidak dapat di update", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "error"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> removeOne(@PathVariable("id") Long id) {
        try {
            Product product = productService.findOne(id);

            if (product != null) {
                productService.removeOne(id);
                return ResponseEntity.ok(ResponseHelper.createResponse("Data berhasil di hapus", null, HttpStatus.OK.value(), "success"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ResponseHelper.createErrorResponse("Data tidak ada", "Product not found with ID: " + id, HttpStatus.NOT_FOUND.value(), "error"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseHelper.createErrorResponse("Data tidak dapat dihapus", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "error"));
        }
    }
}
