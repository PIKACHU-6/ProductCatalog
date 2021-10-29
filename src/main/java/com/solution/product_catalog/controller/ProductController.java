package com.solution.product_catalog.controller;

import com.solution.product_catalog.exception.ResourceNotFoundException;
import com.solution.product_catalog.model.ProductModel;
import com.solution.product_catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/v1/products")
    public ResponseEntity<ProductModel> insertProduct(@RequestBody ProductModel inputProduct) {
        ProductModel model = null;
        try {
            model = service.insertProduct(inputProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(model);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(model);
        }
    }

    @GetMapping("/v1/products/{category}")
    public ResponseEntity<List<ProductModel>> getProducts(@PathVariable String category,
                                                          @RequestParam(defaultValue = "${pagNo}") Integer pageNo,
                                                          @RequestParam(defaultValue = "${pageSize}") Integer pageSize) {
        List<ProductModel> model = null;
        try {
            model = service.getProducts(category, pageNo, pageSize);
            return ResponseEntity.ok(model);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(model);
        }
    }
}
