package store_backend_admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import store_backend_admin.entity.*;

import java.math.BigDecimal;
import java.util.List;

public class CreateProductRequest {

    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @NotBlank
    private String sku;

    private TaxClass taxClass;
    private String description;
    private ProductStatus status;
    private String visibility;
    private ProductType type;

    @PositiveOrZero
    private Integer stock;

    private ManageStock manageStock;
    private StockAvailability stockAvailability;

    @PositiveOrZero
    private Integer quantity;

    private Boolean noShippingRequired;

    @PositiveOrZero
    private BigDecimal weight;

    private String packageName;
    private String attributeGroup;
    private String color;
    private String size;

    @NotBlank
    private String urlKey;

    private String metaTitle;
    private String metaDescription;
    private String customFields;
    private String image;

    @PositiveOrZero
    private Integer categoryId;

    private List<String> media;

    // Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public TaxClass getTaxClass() {
        return taxClass;
    }

    public void setTaxClass(TaxClass taxClass) {
        this.taxClass = taxClass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public ManageStock getManageStock() {
        return manageStock;
    }

    public void setManageStock(ManageStock manageStock) {
        this.manageStock = manageStock;
    }

    public StockAvailability getStockAvailability() {
        return stockAvailability;
    }

    public void setStockAvailability(StockAvailability stockAvailability) {
        this.stockAvailability = stockAvailability;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getNoShippingRequired() {
        return noShippingRequired;
    }

    public void setNoShippingRequired(Boolean noShippingRequired) {
        this.noShippingRequired = noShippingRequired;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAttributeGroup() {
        return attributeGroup;
    }

    public void setAttributeGroup(String attributeGroup) {
        this.attributeGroup = attributeGroup;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(String urlKey) {
        this.urlKey = urlKey;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getCustomFields() {
        return customFields;
    }

    public void setCustomFields(String customFields) {
        this.customFields = customFields;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getMedia() {
        return media;
    }

    public void setMedia(List<String> media) {
        this.media = media;
    }
}