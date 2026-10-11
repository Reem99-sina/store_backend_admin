package store_backend_admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store_backend_admin.entity.Inventory;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    List<Inventory> findByProductId(Integer productId);

    boolean existsBySku(String sku);
}