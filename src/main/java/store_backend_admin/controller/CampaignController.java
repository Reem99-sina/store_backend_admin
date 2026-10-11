package store_backend_admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store_backend_admin.entity.Campaign;
import store_backend_admin.service.CampaignService;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @GetMapping
    public ResponseEntity<List<Campaign>> getCampaigns() {
        return ResponseEntity.ok(
                campaignService.getAllCampaigns()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCampaign(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(
                campaignService.getCampaignById(id)
        );
    }

    @PostMapping
    public ResponseEntity<Campaign> createCampaign(
            @RequestBody Campaign campaign
    ) {
        return ResponseEntity.ok(
                campaignService.createCampaign(campaign)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Campaign> updateCampaign(
            @PathVariable Integer id,
            @RequestBody Campaign campaign
    ) {
        return ResponseEntity.ok(
                campaignService.updateCampaign(id, campaign)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaign(
            @PathVariable Integer id
    ) {
        campaignService.deleteCampaign(id);

        return ResponseEntity.noContent().build();
    }
}