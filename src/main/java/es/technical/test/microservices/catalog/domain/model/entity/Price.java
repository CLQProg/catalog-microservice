package es.technical.test.microservices.catalog.domain.model.entity;


import lombok.Builder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.time.Instant;

/**
 * Price of a product.
 *
 * @param startDate Initial {@link Instant} of application of the amount.
 * @param endDate   Final {@link Instant} of application of the amount.
 * @param priority  Price application disambiguator. If two prices coincide in a range of dates, the one with the highest
 *                  priority is applied.
 * @param amount     PVP or final sale amount.
 * @param currency  ISO currency (v.gr.: EUR).
 */
@NullMarked
@Builder
public record Price(
        @Nullable Long id,
        @Nullable Instant startDate,
        @Nullable Instant endDate,
        @Nullable Integer priority,
        @Nullable Double amount,
        @Nullable String currency
) {

}
