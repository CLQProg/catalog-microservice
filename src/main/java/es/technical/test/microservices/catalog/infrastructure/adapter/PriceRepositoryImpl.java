package es.technical.test.microservices.catalog.infrastructure.adapter;


import es.technical.test.microservices.catalog.domain.model.entity.Price;
import es.technical.test.microservices.catalog.domain.repository.PriceRepository;
import es.technical.test.microservices.catalog.domain.service.query.FindApplicablePriceQuery;
import es.technical.test.microservices.catalog.infrastructure.adapter.jpa.PriceJpaRepository;
import es.technical.test.microservices.catalog.infrastructure.adapter.jpa.entity.PriceDbEntity;
import es.technical.test.microservices.catalog.infrastructure.adapter.jpa.mapper.PriceJpaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@NullMarked
@RequiredArgsConstructor
@CacheConfig(cacheNames = "CatalogMicroservice:Prices")
@Component
public class PriceRepositoryImpl implements PriceRepository {


    private final PriceJpaRepository priceJpaRepository;
    private final PriceJpaMapper priceJpaMapper;


    @Cacheable(key = "#query.brandId() + '_' + #query.productId() + '_' + #query.instant().toEpochMilli()")
    @Override
    public List<Price> findAllBy(FindApplicablePriceQuery query) {
        List<PriceDbEntity> dbEntities = priceJpaRepository.findByBrandAndProductAndInstant(query.brandId(),
                query.productId(),
                query.instant());
        return priceJpaMapper.map(dbEntities);
    }

}
