package es.technical.test.microservices.catalog.domain.service;

import es.technical.test.microservices.catalog.domain.model.entity.Brand;
import es.technical.test.microservices.catalog.domain.model.entity.Price;
import es.technical.test.microservices.catalog.domain.model.entity.Product;
import es.technical.test.microservices.catalog.domain.repository.PriceRepository;
import es.technical.test.microservices.catalog.domain.service.query.FindApplicablePriceQuery;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Optional;

@NullMarked
public class ProductServiceImpl implements ProductService {


    private final PriceRepository priceRepository;


    public ProductServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }


    @Override
    public Optional<Product> findWithApplicablePriceBy(FindApplicablePriceQuery query) {
        List<Price> prices = priceRepository.findAllBy(query);
        if (prices.isEmpty()) return Optional.empty();
        return Optional.of(Product.builder()
                .id(query.productId())
                .brand(new Brand(query.brandId(), null))
                .prices(prices)
                .build());
    }

}
