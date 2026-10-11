package store_backend_admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store_backend_admin.entity.Campaign;
import store_backend_admin.entity.Promotion;
import store_backend_admin.exception.CampaignNotFoundException;
import store_backend_admin.repository.CampaignRepository;
import store_backend_admin.repository.PromotionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final PromotionRepository promotionRepository;

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign getCampaignById(Integer id) {
        return campaignRepository.findById(id)
                .orElseThrow(() ->
                        new CampaignNotFoundException(
                                "Campaign not found with id: " + id
                        )
                );
    }

    @Transactional
    public Campaign createCampaign(Campaign campaign) {

        validateDates(
                campaign.getStartDate(),
                campaign.getEndDate()
        );

        return campaignRepository.save(campaign);
    }

    @Transactional
    public Campaign updateCampaign(
            Integer id,
            Campaign campaignData
    ) {

        Campaign campaign = getCampaignById(id);

        LocalDateTime startDate =
                campaignData.getStartDate() != null
                        ? campaignData.getStartDate()
                        : campaign.getStartDate();

        LocalDateTime endDate =
                campaignData.getEndDate() != null
                        ? campaignData.getEndDate()
                        : campaign.getEndDate();

        validateDates(startDate, endDate);

        if (campaignData.getName() != null) {
            campaign.setName(campaignData.getName());
        }

        if (campaignData.getDescription() != null) {
            campaign.setDescription(campaignData.getDescription());
        }

        if (campaignData.getStartDate() != null) {
            campaign.setStartDate(campaignData.getStartDate());
        }

        if (campaignData.getEndDate() != null) {
            campaign.setEndDate(campaignData.getEndDate());
        }

        return campaignRepository.save(campaign);
    }

    @Transactional
    public void deleteCampaign(Integer id) {

        Campaign campaign = getCampaignById(id);

        List<Promotion> promotions =
                promotionRepository.findAll();

        promotions.stream()
                .filter(p ->
                        p.getCampaign() != null &&
                        p.getCampaign().getId().equals(id)
                )
                .forEach(p -> {
                    p.setCampaign(null);
                    promotionRepository.save(p);
                });

        campaignRepository.delete(campaign);
    }

    private void validateDates(
            LocalDateTime startDate,
            LocalDateTime endDate
    ) {
        if (
                startDate != null &&
                endDate != null &&
                startDate.isAfter(endDate)
        ) {
            throw new RuntimeException(
                    "Start date cannot be after end date."
            );
        }
    }
}