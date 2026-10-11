package store_backend_admin.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import store_backend_admin.dto.CreateProductRequest;
import store_backend_admin.dto.UpdateProductRequest;
import store_backend_admin.entity.Product;
import store_backend_admin.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // PUBLIC
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // PUBLIC
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable Integer id
    ) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ADMIN
    @PostMapping
    public Product createProduct(
            @Valid @RequestBody CreateProductRequest request
    ) {
        return productService.createProduct(request);
    }

    // ADMIN
    @PatchMapping("/{id}")
    public Product updateProduct(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateProductRequest request
    ) {
        return productService.updateProduct(id, request);
    }

    // ADMIN
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Integer id
    ) {
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}