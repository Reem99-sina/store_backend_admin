package store_backend_admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store_backend_admin.entity.Campaign;
import store_backend_admin.entity.Promotion;
import store_backend_admin.repository.CampaignRepository;
import store_backend_admin.repository.PromotionRepository;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final CampaignRepository campaignRepository;

    @Transactional
    public Promotion createPromotion(
            Promotion promotion,
            String campaignChoice,
            Integer campaignId,
            Campaign newCampaign
    ) {

        // Check promotion code
        if (promotion.getCode() != null &&
                promotionRepository.existsByCode(promotion.getCode())) {

            throw new RuntimeException("Promotion code already exists.");
        }

        Integer finalCampaignId = campaignId;

        // Create new campaign
        if ("new".equals(campaignChoice)) {

            if (newCampaign == null) {
                throw new RuntimeException("Campaign data is required.");
            }

            Campaign campaign = new Campaign();

            campaign.setName(newCampaign.getName());
            campaign.setDescription(newCampaign.getDescription());
            campaign.setStartDate(newCampaign.getStartDate());
            campaign.setEndDate(newCampaign.getEndDate());

            Campaign savedCampaign =
                    campaignRepository.save(campaign);

            finalCampaignId = savedCampaign.getId();

            promotion.setCampaign(savedCampaign);
            promotion.setCampaignChoice("new");
        }

        // Existing campaign
        if ("existing".equals(campaignChoice)) {

            if (finalCampaignId == null) {
                throw new RuntimeException("Campaign is required.");
            }

            Campaign campaign = campaignRepository.findById(finalCampaignId)
                    .orElseThrow(() ->
                            new RuntimeException("Campaign not found.")
                    );

            promotion.setCampaign(campaign);
            promotion.setCampaignChoice("existing");
        }

        // No campaign
        if ("none".equals(campaignChoice)) {
            promotion.setCampaign(null);
            promotion.setCampaignChoice("none");
        }

        return promotionRepository.save(promotion);
    }
    public java.util.List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Promotion getPromotionById(Integer id) {
        return promotionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Promotion not found.")
                );
    }

    @Transactional
    public Promotion updatePromotion(
            Integer id,
            Promotion promotionData,
            String campaignChoice,
            Integer campaignId
    ) {
        Promotion promotion = getPromotionById(id);

        if (promotionData.getType() != null) {
            promotion.setType(promotionData.getType());
        }

        if (promotionData.getCode() != null &&
                !promotionData.getCode().equals(promotion.getCode())) {

            if (promotionRepository.existsByCode(promotionData.getCode())) {
                throw new RuntimeException("Promotion code already exists.");
            }

            promotion.setCode(promotionData.getCode());
        }

        if (promotionData.getIsAutomatic() != null) {
            promotion.setIsAutomatic(promotionData.getIsAutomatic());
        }

        if (promotionData.getAutomatic() != null) {
            promotion.setAutomatic(promotionData.getAutomatic());
        }

        if (promotionData.getStatus() != null) {
            promotion.setStatus(promotionData.getStatus());
        }

        if (promotionData.getCurrency() != null) {
            promotion.setCurrency(promotionData.getCurrency());
        }

        if (promotionData.getIsTaxInclusive() != null) {
            promotion.setIsTaxInclusive(
                    promotionData.getIsTaxInclusive()
            );
        }

        promotion.setDiscountAmount(
                promotionData.getDiscountAmount()
        );

        promotion.setPercentage(
                promotionData.getPercentage()
        );

        promotion.setBuyQuantity(
                promotionData.getBuyQuantity()
        );

        promotion.setGetQuantity(
                promotionData.getGetQuantity()
        );

        promotion.setUsageLimit(
                promotionData.getUsageLimit()
        );

        if (promotionData.getUsageCount() != null) {
            promotion.setUsageCount(
                    promotionData.getUsageCount()
            );
        }

        promotion.setStartDate(
                promotionData.getStartDate()
        );

        promotion.setEndDate(
                promotionData.getEndDate()
        );

        promotion.setCustomerGroup(
                promotionData.getCustomerGroup()
        );

        promotion.setSalesChannel(
                promotionData.getSalesChannel()
        );

        promotion.setCustomerConditions(
                promotionData.getCustomerConditions()
        );

        promotion.setApplicationMethod(
                promotionData.getApplicationMethod()
        );

        if (campaignChoice != null) {

            promotion.setCampaignChoice(campaignChoice);

            if ("none".equals(campaignChoice)) {
                promotion.setCampaign(null);
            }

            if ("existing".equals(campaignChoice)) {

                if (campaignId == null) {
                    throw new RuntimeException("Campaign is required.");
                }

                Campaign campaign = campaignRepository.findById(campaignId)
                        .orElseThrow(() ->
                                new RuntimeException("Campaign not found.")
                        );

                promotion.setCampaign(campaign);
            }
        }

        return promotionRepository.save(promotion);
    }

    @Transactional
    public void deletePromotion(Integer id) {
        Promotion promotion = getPromotionById(id);
        promotionRepository.delete(promotion);
    }
}