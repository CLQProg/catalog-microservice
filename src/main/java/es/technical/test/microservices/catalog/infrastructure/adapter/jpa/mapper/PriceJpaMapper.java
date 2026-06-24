package es.technical.test.microservices.catalog.infrastructure.adapter.jpa.mapper;

import es.technical.test.microservices.catalog.domain.model.entity.Price;
import es.technical.test.microservices.catalog.infrastructure.adapter.jpa.entity.PriceDbEntity;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.mapstruct.Mapper;

import java.util.List;

@NullMarked
@Mapper(componentModel = "spring")
public interface PriceJpaMapper {

    @Nullable Price map(@Nullable PriceDbEntity dbEntity);
    @Nullable List<Price> map(@Nullable List<PriceDbEntity> dbEntities);

}
