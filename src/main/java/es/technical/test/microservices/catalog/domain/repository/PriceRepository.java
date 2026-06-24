package es.technical.test.microservices.catalog.domain.repository;

import es.technical.test.microservices.catalog.domain.model.entity.Price;
import es.technical.test.microservices.catalog.domain.service.query.FindApplicablePriceQuery;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Optional;


@NullMarked
public interface PriceRepository {


    /**
     * Find information on the applicable prices of a product based on the group&#39;s brand and an application date.
     *
     * @param query {@link FindApplicablePriceQuery}.
     * @return  {@link Optional} of {@link Price}.
     */
    List<Price> findAllBy(FindApplicablePriceQuery query);

}
