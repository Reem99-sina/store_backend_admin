package store_backend_admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store_backend_admin.entity.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
}