package com.target.indexing.controller;

import com.target.indexing.document.ProductDocument;
import com.target.indexing.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elastic/v1/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDocument> findAll() throws Exception {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDocument findById(@PathVariable String id) throws Exception {
        return productService.findById(id);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductDocument productDocument) throws Exception {
        return new ResponseEntity(productService.createProductDocument(productDocument), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody ProductDocument productDocument) throws Exception {
        return new ResponseEntity(productService.updateProduct(productDocument), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id) throws Exception {
        return productService.deleteProductDocument(id);
    }

    @GetMapping(value = "/search-sellerId")
    public List<ProductDocument> searchById(@RequestParam(value = "sellerid") String id) throws Exception {
        return productService.findProductBySellerId(id);
    }
}
