package store_backend_admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store_backend_admin.entity.ProductMedia;

import java.util.List;

public interface ProductMediaRepository extends JpaRepository<ProductMedia, Integer> {

    List<ProductMedia> findByProductIdOrderByPositionAsc(Integer productId);

    void deleteByProductId(Integer productId);
}