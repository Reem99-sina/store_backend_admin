package store_backend_admin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "promotions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "type", columnDefinition = "\"PromotionType\"", nullable = false)
    private PromotionType type;

    @Column(unique = true)
    private String code;

    @Column(name = "\"isAutomatic\"")
    private Boolean isAutomatic = false;

    @Column(name = "\"automatic\"")
    private Boolean automatic = false;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "\"status\"", columnDefinition = "\"PromotionStatus\"")
    private PromotionStatus status = PromotionStatus.DRAFT;

    @Column(name = "\"currency\"")
    private String currency = "USD";

    @Column(name = "\"isTaxInclusive\"")
    private Boolean isTaxInclusive = false;

    @Column(name = "\"discountAmount\"", precision = 12, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "\"percentage\"", precision = 5, scale = 2)
    private BigDecimal percentage;

    @Column(name = "\"buyQuantity\"")
    private Integer buyQuantity;

    @Column(name = "\"getQuantity\"")
    private Integer getQuantity;

    @Column(name = "\"usageLimit\"")
    private Integer usageLimit;

    @Column(name = "\"usageCount\"")
    private Integer usageCount = 0;

    @Column(name = "\"startDate\"")
    private LocalDateTime startDate;

    @Column(name = "\"endDate\"")
    private LocalDateTime endDate;

    @Column(name = "\"customerGroup\"")
    private String customerGroup;

    @Column(name = "\"salesChannel\"")
    private String salesChannel;

    @Column(name = "\"campaignChoice\"")
    private String campaignChoice;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "\"customerConditions\"", columnDefinition = "jsonb")
    private String customerConditions;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "\"applicationMethod\"", columnDefinition = "jsonb")
    private String applicationMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"campaignId\"")
    private Campaign campaign;

    @Column(name = "\"createdAt\"")
    private LocalDateTime createdAt;

    @Column(name = "\"updatedAt\"")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {

        if (usageCount == null) {
            usageCount = 0;
        }

        if (isAutomatic == null) {
            isAutomatic = false;
        }

        if (automatic == null) {
            automatic = false;
        }

        if (isTaxInclusive == null) {
            isTaxInclusive = false;
        }

        if (currency == null) {
            currency = "USD";
        }

        if (status == null) {
            status = PromotionStatus.DRAFT;
        }

        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}