package store_backend_admin.service;

import org.springframework.stereotype.Service;

import store_backend_admin.dto.CreateCategoryRequest;
import store_backend_admin.dto.UpdateCategoryRequest;
import store_backend_admin.entity.Category;
import store_backend_admin.entity.UserStatus;
import store_backend_admin.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // GET ALL
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // GET BY ID
    public Optional<Category> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    // CREATE
    public Category createCategory(CreateCategoryRequest request) {

        // Check URL key uniqueness
        if (categoryRepository.existsByUrlKey(request.getUrlKey())) {
            throw new RuntimeException(
                    "Category URL key already exists."
            );
        }

        // Check parent category
        if (request.getParentId() != null) {
            categoryRepository.findById(request.getParentId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Parent category not found."
                            ));
        }

        Category category = new Category();

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setUrlKey(request.getUrlKey());
        category.setMetaTitle(request.getMetaTitle());
        category.setMetaDescription(request.getMetaDescription());
        category.setImage(request.getImage());

        category.setStatus(
                request.getStatus() != null
                        ? request.getStatus()
                        : UserStatus.ACTIVE
        );

        category.setCustomFields(request.getCustomFields());
        category.setParentId(request.getParentId());

        return categoryRepository.save(category);
    }

    // UPDATE
    public Category updateCategory(
            Integer id,
            UpdateCategoryRequest request
    ) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category not found."
                        ));

        // Check URL key uniqueness
        if (request.getUrlKey() != null
                && !request.getUrlKey().equals(category.getUrlKey())) {

            if (categoryRepository.existsByUrlKey(
                    request.getUrlKey()
            )) {
                throw new RuntimeException(
                        "Category URL key already exists."
                );
            }

            category.setUrlKey(request.getUrlKey());
        }

        // Check parent
        if (request.getParentId() != null) {

            if (request.getParentId().equals(id)) {
                throw new RuntimeException(
                        "Category cannot be its own parent."
                );
            }

            categoryRepository.findById(request.getParentId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Parent category not found."
                            ));

            category.setParentId(request.getParentId());

        } else {
            category.setParentId(null);
        }

        if (request.getName() != null) {
            category.setName(request.getName());
        }

        if (request.getDescription() != null) {
            category.setDescription(request.getDescription());
        }

        if (request.getMetaTitle() != null) {
            category.setMetaTitle(request.getMetaTitle());
        }

        if (request.getMetaDescription() != null) {
            category.setMetaDescription(
                    request.getMetaDescription()
            );
        }

        if (request.getImage() != null) {
            category.setImage(request.getImage());
        }

        if (request.getStatus() != null) {
            category.setStatus(request.getStatus());
        }

        if (request.getCustomFields() != null) {
            category.setCustomFields(
                    request.getCustomFields()
            );
        }

        return categoryRepository.save(category);
    }

    // DELETE
    public void deleteCategory(Integer id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category not found."
                        ));

        // Check child categories
        if (categoryRepository.existsByParentId(id)) {
            throw new RuntimeException(
                    "Cannot delete category because it has child categories."
            );
        }

        categoryRepository.delete(category);
    }
}