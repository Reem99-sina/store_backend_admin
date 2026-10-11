package store_backend_admin.service;

import org.springframework.stereotype.Service;
import store_backend_admin.dto.CreateProductRequest;
import store_backend_admin.dto.UpdateProductRequest;
import store_backend_admin.entity.Product;
import store_backend_admin.entity.ProductMedia;
import store_backend_admin.repository.CategoryRepository;
import store_backend_admin.repository.InventoryRepository;
import store_backend_admin.repository.ProductMediaRepository;
import store_backend_admin.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMediaRepository productMediaRepository;
    private final InventoryRepository inventoryRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(
            ProductRepository productRepository,
            ProductMediaRepository productMediaRepository,
            InventoryRepository inventoryRepository,
            CategoryRepository categoryRepository
    ) {
        this.productRepository = productRepository;
        this.productMediaRepository = productMediaRepository;
        this.inventoryRepository = inventoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Product createProduct(CreateProductRequest request) {

        if (productRepository.existsBySku(request.getSku())) {
            throw new RuntimeException("Product SKU already exists.");
        }

        if (productRepository.existsByUrlKey(request.getUrlKey())) {
            throw new RuntimeException("Product URL key already exists.");
        }

        if (request.getCategoryId() != null) {
            categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() ->
                            new RuntimeException("Category not found."));
        }

        Product product = new Product();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setSku(request.getSku());
        product.setTaxClass(request.getTaxClass());
        product.setDescription(request.getDescription());
        product.setStatus(request.getStatus());
        product.setVisibility(request.getVisibility());
        product.setType(request.getType());
        product.setStock(request.getStock());
        product.setManageStock(request.getManageStock());
        product.setStockAvailability(request.getStockAvailability());
        product.setQuantity(request.getQuantity());
        product.setNoShippingRequired(request.getNoShippingRequired());
        product.setWeight(request.getWeight());
        product.setPackageName(request.getPackageName());
        product.setAttributeGroup(request.getAttributeGroup());
        product.setColor(request.getColor());
        product.setSize(request.getSize());
        product.setUrlKey(request.getUrlKey());
        product.setMetaTitle(request.getMetaTitle());
        product.setMetaDescription(request.getMetaDescription());
        product.setCustomFields(request.getCustomFields());
        product.setCategoryId(request.getCategoryId());

        /*
         * Same logic as Node:
         *
         * image = request.image || media[0] || null
         */
        String mainImage = request.getImage();

        if ((mainImage == null || mainImage.isBlank())
                && request.getMedia() != null
                && !request.getMedia().isEmpty()) {

            mainImage = request.getMedia().get(0);
        }

        product.setImage(mainImage);

        Product savedProduct = productRepository.save(product);

        /*
         * Create ProductMedia records
         */
        if (request.getMedia() != null) {

            int position = 0;

            for (String url : request.getMedia()) {

                if (url == null || url.isBlank()) {
                    continue;
                }

                ProductMedia media = new ProductMedia();

                media.setUrl(url);
                media.setPosition(position++);
                media.setProductId(savedProduct.getId());

                productMediaRepository.save(media);
            }
        }

        return savedProduct;
    }
    public Product updateProduct(Integer id, UpdateProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found."));

        if (request.getSku() != null
                && !request.getSku().equals(product.getSku())) {

            if (productRepository.existsBySku(request.getSku())) {
                throw new RuntimeException("Product SKU already exists.");
            }

            product.setSku(request.getSku());
        }

        if (request.getUrlKey() != null
                && !request.getUrlKey().equals(product.getUrlKey())) {

            if (productRepository.existsByUrlKey(request.getUrlKey())) {
                throw new RuntimeException("Product URL key already exists.");
            }

            product.setUrlKey(request.getUrlKey());
        }

        if (request.getCategoryId() != null) {

            categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() ->
                            new RuntimeException("Category not found."));

            product.setCategoryId(request.getCategoryId());
        }

        if (request.getName() != null) {
            product.setName(request.getName());
        }

        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }

        if (request.getTaxClass() != null) {
            product.setTaxClass(request.getTaxClass());
        }

        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }

        if (request.getStatus() != null) {
            product.setStatus(request.getStatus());
        }

        if (request.getVisibility() != null) {
            product.setVisibility(request.getVisibility());
        }

        if (request.getType() != null) {
            product.setType(request.getType());
        }

        if (request.getStock() != null) {
            product.setStock(request.getStock());
        }

        if (request.getManageStock() != null) {
            product.setManageStock(request.getManageStock());
        }

        if (request.getStockAvailability() != null) {
            product.setStockAvailability(request.getStockAvailability());
        }

        if (request.getQuantity() != null) {
            product.setQuantity(request.getQuantity());
        }

        if (request.getNoShippingRequired() != null) {
            product.setNoShippingRequired(request.getNoShippingRequired());
        }

        if (request.getWeight() != null) {
            product.setWeight(request.getWeight());
        }

        if (request.getPackageName() != null) {
            product.setPackageName(request.getPackageName());
        }

        if (request.getAttributeGroup() != null) {
            product.setAttributeGroup(request.getAttributeGroup());
        }

        if (request.getColor() != null) {
            product.setColor(request.getColor());
        }

        if (request.getSize() != null) {
            product.setSize(request.getSize());
        }

        if (request.getMetaTitle() != null) {
            product.setMetaTitle(request.getMetaTitle());
        }

        if (request.getMetaDescription() != null) {
            product.setMetaDescription(request.getMetaDescription());
        }

        if (request.getCustomFields() != null) {
            product.setCustomFields(request.getCustomFields());
        }

        if (request.getImage() != null) {
            product.setImage(request.getImage());
        }

        /*
         * Same behavior as Node:
         * If media is provided, delete old media
         * and create the new media list.
         */
        if (request.getMedia() != null) {

            productMediaRepository.deleteByProductId(id);

            int position = 0;

            for (String url : request.getMedia()) {

                if (url == null || url.isBlank()) {
                    continue;
                }

                ProductMedia media = new ProductMedia();

                media.setUrl(url);
                media.setPosition(position++);
                media.setProductId(id);

                productMediaRepository.save(media);
            }

            /*
             * If image wasn't explicitly sent,
             * use the first media as the main image.
             */
            if ((request.getImage() == null
                    || request.getImage().isBlank())
                    && !request.getMedia().isEmpty()) {

                product.setImage(request.getMedia().get(0));
            }
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found."));

        productMediaRepository.deleteByProductId(id);

        productRepository.delete(product);
    }
}