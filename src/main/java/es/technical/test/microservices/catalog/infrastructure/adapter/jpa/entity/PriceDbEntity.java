package es.technical.test.microservices.catalog.infrastructure.adapter.jpa.entity;


import es.technical.test.microservices.catalog.domain.model.entity.Price;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;

/**
 * Database entity that represents a {@link Price} of a product.
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "prices")
public class PriceDbEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "price_list", nullable = false)
    Long id;

    /**
     * Initial instant of application of the amount.
     */
    @Setter
    @Column(name = "start_date", nullable = false, columnDefinition = "TIMESTAMP")
    Instant startDate;

    /**
     * Final instant of application of the amount.
     */
    @Setter
    @Column(name = "end_date", nullable = false, columnDefinition = "TIMESTAMP")
    Instant endDate;

    /**
     * Price application disambiguator. If two catalog coincide in a range of dates, the one with the highest priority
     * (highest numerical value) is applied.
     */
    @Setter
    @Column(name = "priority")
    Long priority;

    /**
     * PVP or final sale amount.
     */
    @Setter
    @Column(name = "price", nullable = false)
    Double amount;

    @Setter
    @Length(min = 3, max = 3)
    @Column(name = "curr", nullable = false)
    String currency;

    @Column(name = "product_id", nullable = false)
    Long productEntityId;

    @Column(name = "brand_id", nullable = false)
    Long brandEntityId;

}
