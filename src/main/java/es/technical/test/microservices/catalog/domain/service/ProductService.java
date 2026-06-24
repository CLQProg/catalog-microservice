package es.technical.test.microservices.catalog.domain.service;

import es.technical.test.microservices.catalog.domain.model.entity.Product;
import es.technical.test.microservices.catalog.domain.service.query.FindApplicablePriceQuery;
import org.jspecify.annotations.NullMarked;

import java.util.Optional;

@NullMarked
public interface ProductService {

    /**
     * Find the applicable amount of a product based on the group's brand and an application date.
     *
     * @param query {@link FindApplicablePriceQuery}
     * @return {@link Optional} of {@link Product}
     */
    Optional<Product> findWithApplicablePriceBy(FindApplicablePriceQuery query);

}
