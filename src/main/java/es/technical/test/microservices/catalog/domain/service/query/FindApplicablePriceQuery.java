package es.technical.test.microservices.catalog.domain.service.query;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.time.Instant;

/**
 * FindApplicablePriceQuery.
 *
 * @param brandId   Brand ID (required)
 * @param productId Product ID (required)
 * @param instant   Date of application of the product&#39;s amount (required)
 */
@NullMarked
public record FindApplicablePriceQuery(
        long brandId, long productId, Instant instant
) {

    public FindApplicablePriceQuery(long brandId, long productId, @Nullable Instant instant) {
        this.brandId = brandId;
        this.productId = productId;
        if (instant == null) this.instant = Instant.now();
        else this.instant = instant;
    }

}
