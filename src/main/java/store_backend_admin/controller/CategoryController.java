package store_backend_admin.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import store_backend_admin.dto.CreateCategoryRequest;
import store_backend_admin.dto.UpdateCategoryRequest;
import store_backend_admin.entity.Category;
import store_backend_admin.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // GET /categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // GET /categories/:id
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(
            @PathVariable Integer id
    ) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /categories
    @PostMapping
    public ResponseEntity<Category> createCategory(
            @Valid @RequestBody CreateCategoryRequest request
    ) {
        Category category = categoryService.createCategory(request);

        return ResponseEntity.ok(category);
    }

    // PATCH /categories/:id
    @PatchMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateCategoryRequest request
    ) {
        Category category = categoryService.updateCategory(id, request);

        return ResponseEntity.ok(category);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Integer id
    ) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}