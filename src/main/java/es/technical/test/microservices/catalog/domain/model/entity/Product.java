package es.technical.test.microservices.catalog.domain.model.entity;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@NullMarked
@Getter
public class Product {

    private final Long id;
    @Nullable private final Brand brand;
    @Nullable private final Price applicablePrice;
    private final List<Price> prices;

    @Builder
    public Product(Long id, @Nullable Brand brand, @Nullable List<Price> prices) {
        this.id = id;
        this.brand = brand;
        this.prices = prices != null ? prices : new ArrayList<>();
        Optional<Price> optMaxPriorityPrice = this.prices.stream()
                .max(Comparator.nullsFirst(Comparator.comparing(Price::priority, Comparator.nullsFirst(Comparator.naturalOrder()))));
        this.applicablePrice = optMaxPriorityPrice.orElse(null);

    }

}
