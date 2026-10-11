package store_backend_admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store_backend_admin.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByUrlKey(String urlKey);

    boolean existsByParentId(Integer parentId);
}