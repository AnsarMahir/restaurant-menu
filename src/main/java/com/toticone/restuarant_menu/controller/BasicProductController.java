package com.toticone.restuarant_menu.controller;

import com.toticone.restuarant_menu.dto.BasicProductDTO;
import com.toticone.restuarant_menu.service.BasicProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class BasicProductController {

    @Autowired
    private BasicProductService productService;

    @GetMapping
    public ResponseEntity<List<BasicProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasicProductDTO> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<BasicProductDTO> createProduct(@RequestBody @Valid BasicProductDTO productDTO) {
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BasicProductDTO> updateProduct(@PathVariable int id, @RequestBody BasicProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateProduct(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<BasicProductDTO>> getProductsByType(@PathVariable int type) {
        return ResponseEntity.ok(productService.getProductsByType(type));
    }

    @PostMapping("/{productId}/extras/{extraId}")
    public ResponseEntity<BasicProductDTO> addExtraToProduct(@PathVariable int productId, @PathVariable int extraId) {
        return ResponseEntity.ok(productService.addExtraToProduct(productId, extraId));
    }

    @DeleteMapping("/{productId}/extras/{extraId}")
    public ResponseEntity<BasicProductDTO> removeExtraFromProduct(@PathVariable int productId, @PathVariable int extraId) {
        return ResponseEntity.ok(productService.removeExtraFromProduct(productId, extraId));
    }
}