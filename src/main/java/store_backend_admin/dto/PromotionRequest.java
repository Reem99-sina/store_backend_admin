package store_backend_admin.dto;

import lombok.Getter;
import lombok.Setter;
import store_backend_admin.entity.Campaign;
import store_backend_admin.entity.Promotion;

@Getter
@Setter
public class PromotionRequest {

    private Promotion promotion;

    private String campaignChoice;

    private Integer campaignId;

    private Campaign campaign;
}