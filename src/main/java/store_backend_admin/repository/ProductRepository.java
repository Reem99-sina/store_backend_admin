package store_backend_admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store_backend_admin.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsBySku(String sku);

    boolean existsByUrlKey(String urlKey);
}