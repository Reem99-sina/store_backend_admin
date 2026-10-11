package store_backend_admin.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, unique = true)
    private String sku;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "\"taxClass\"", columnDefinition = "\"TaxClass\"")
    private TaxClass taxClass = TaxClass.TAXABLE;

    private String description;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "status", columnDefinition = "\"ProductStatus\"")
    private ProductStatus status = ProductStatus.ENABLE;

    private String visibility;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "type", columnDefinition = "\"ProductType\"")
    private ProductType type = ProductType.SIMPLE;

    private Integer stock = 0;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "\"manageStock\"", columnDefinition = "\"ManageStock\"")
    private ManageStock manageStock = ManageStock.YES;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "\"stockAvailability\"", columnDefinition = "\"StockAvailability\"")
    private StockAvailability stockAvailability = StockAvailability.IN_STOCK;

    private Integer quantity = 0;

    @Column(name = "\"noShippingRequired\"")
    private Boolean noShippingRequired = false;

    @Column(precision = 10, scale = 3)
    private BigDecimal weight;

    @Column(name = "package")
    private String packageName;

    @Column(name = "\"attributeGroup\"")
    private String attributeGroup;

    private String color;

    private String size;

    @Column(name = "\"urlKey\"", nullable = false, unique = true)
    private String urlKey;

    @Column(name = "\"metaTitle\"")
    private String metaTitle;

    @Column(name = "\"metaDescription\"")
    private String metaDescription;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "\"customFields\"", columnDefinition = "jsonb")
    private String customFields;

    private String image;

    @Column(name = "\"createdAt\"")
    private LocalDateTime createdAt;

    @Column(name = "\"updatedAt\"")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Column(name = "\"categoryId\"")
    private Integer categoryId;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}