package com.app.microserviceproduit.controller;

import com.app.microserviceproduit.exceptions.ProductNotFoundException;
import com.app.microserviceproduit.model.Product;
import com.app.microserviceproduit.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product not found with id " + id);
        }
        return ResponseEntity.ok(product.get());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product not found with id " + id);
        }
        Product existingProduct = product.get();
        existingProduct.setTitre(productDetails.getTitre());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setImage(productDetails.getImage());
        existingProduct.setPrix(productDetails.getPrix());
        Product updatedProduct = productRepository.save(existingProduct);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product not found with id " + id);
        }
        productRepository.delete(product.get());
        return ResponseEntity.ok().build();
    }
}