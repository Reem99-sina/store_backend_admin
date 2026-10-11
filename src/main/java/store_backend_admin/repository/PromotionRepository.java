package store_backend_admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store_backend_admin.entity.Promotion;

import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> {

    Optional<Promotion> findByCode(String code);

    boolean existsByCode(String code);
}