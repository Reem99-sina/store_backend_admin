package store_backend_admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store_backend_admin.dto.PromotionRequest;
import store_backend_admin.entity.Promotion;
import store_backend_admin.service.PromotionService;

import java.util.List;

@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    @PostMapping
    public ResponseEntity<Promotion> createPromotion(
            @RequestBody PromotionRequest request
    ) {
        Promotion promotion = promotionService.createPromotion(
                request.getPromotion(),
                request.getCampaignChoice(),
                request.getCampaignId(),
                request.getCampaign()
        );

        return ResponseEntity.ok(promotion);
    }

    @GetMapping
    public ResponseEntity<List<Promotion>> getPromotions() {
        return ResponseEntity.ok(
                promotionService.getAllPromotions()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promotion> getPromotion(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(
                promotionService.getPromotionById(id)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Promotion> updatePromotion(
            @PathVariable Integer id,
            @RequestBody PromotionRequest request
    ) {
        Promotion promotion = promotionService.updatePromotion(
                id,
                request.getPromotion(),
                request.getCampaignChoice(),
                request.getCampaignId()
        );

        return ResponseEntity.ok(promotion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotion(
            @PathVariable Integer id
    ) {
        promotionService.deletePromotion(id);

        return ResponseEntity.noContent().build();
    }
}