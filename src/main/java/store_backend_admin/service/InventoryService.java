package store_backend_admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store_backend_admin.entity.Inventory;
import store_backend_admin.repository.InventoryRepository;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // GET ALL
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    // GET BY ID
    public Inventory getInventoryById(Integer id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Inventory not found with id: " + id
                        )
                );
    }

    // GET BY PRODUCT ID
    public List<Inventory> getByProductId(Integer productId) {
        return inventoryRepository.findByProductId(productId);
    }

    // ADD
    @Transactional
    public Inventory createInventory(Inventory inventory) {

        if (inventoryRepository.existsBySku(inventory.getSku())) {
            throw new RuntimeException(
                    "Inventory with SKU already exists: " + inventory.getSku()
            );
        }

        if (inventory.getReserved() == null) {
            inventory.setReserved(0);
        }

        if (inventory.getInStock() == null) {
            inventory.setInStock(0);
        }

        if (inventory.getRequiresShipping() == null) {
            inventory.setRequiresShipping(true);
        }

        return inventoryRepository.save(inventory);
    }

    // UPDATE
    @Transactional
    public Inventory updateInventory(
            Integer id,
            Inventory inventoryData
    ) {
        Inventory inventory = getInventoryById(id);

        if (inventoryData.getSku() != null &&
                !inventoryData.getSku().equals(inventory.getSku())) {

            if (inventoryRepository.existsBySku(inventoryData.getSku())) {
                throw new RuntimeException(
                        "Inventory with SKU already exists: "
                                + inventoryData.getSku()
                );
            }

            inventory.setSku(inventoryData.getSku());
        }

        if (inventoryData.getTitle() != null) {
            inventory.setTitle(inventoryData.getTitle());
        }

        if (inventoryData.getDescription() != null) {
            inventory.setDescription(inventoryData.getDescription());
        }

        if (inventoryData.getRequiresShipping() != null) {
            inventory.setRequiresShipping(
                    inventoryData.getRequiresShipping()
            );
        }

        if (inventoryData.getWidth() != null) {
            inventory.setWidth(inventoryData.getWidth());
        }

        if (inventoryData.getLength() != null) {
            inventory.setLength(inventoryData.getLength());
        }

        if (inventoryData.getHeight() != null) {
            inventory.setHeight(inventoryData.getHeight());
        }

        if (inventoryData.getWeight() != null) {
            inventory.setWeight(inventoryData.getWeight());
        }

        if (inventoryData.getMidCode() != null) {
            inventory.setMidCode(inventoryData.getMidCode());
        }

        if (inventoryData.getHsCode() != null) {
            inventory.setHsCode(inventoryData.getHsCode());
        }

        if (inventoryData.getCountry() != null) {
            inventory.setCountry(inventoryData.getCountry());
        }

        if (inventoryData.getMaterial() != null) {
            inventory.setMaterial(inventoryData.getMaterial());
        }

        if (inventoryData.getReserved() != null) {
            inventory.setReserved(inventoryData.getReserved());
        }

        if (inventoryData.getInStock() != null) {
            inventory.setInStock(inventoryData.getInStock());
        }

        if (inventoryData.getProductId() != null) {
            inventory.setProductId(inventoryData.getProductId());
        }

        return inventoryRepository.save(inventory);
    }

    // DELETE
    @Transactional
    public void deleteInventory(Integer id) {
        Inventory inventory = getInventoryById(id);
        inventoryRepository.delete(inventory);
    }
}