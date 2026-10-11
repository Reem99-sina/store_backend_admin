package store_backend_admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store_backend_admin.entity.Inventory;
import store_backend_admin.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventories() {
        return ResponseEntity.ok(
                inventoryService.getAllInventories()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventory(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(
                inventoryService.getInventoryById(id)
        );
    }

    // GET BY PRODUCT ID
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Inventory>> getByProductId(
            @PathVariable Integer productId
    ) {
        return ResponseEntity.ok(
                inventoryService.getByProductId(productId)
        );
    }

    // ADD
    @PostMapping
    public ResponseEntity<Inventory> createInventory(
            @RequestBody Inventory inventory
    ) {
        return ResponseEntity.ok(
                inventoryService.createInventory(inventory)
        );
    }

    // UPDATE
    @PatchMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(
            @PathVariable Integer id,
            @RequestBody Inventory inventory
    ) {
        return ResponseEntity.ok(
                inventoryService.updateInventory(id, inventory)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(
            @PathVariable Integer id
    ) {
        inventoryService.deleteInventory(id);

        return ResponseEntity.noContent().build();
    }
}