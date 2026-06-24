package es.technical.test.microservices.catalog.application.ecommerce.rest.mapper;

import es.technical.test.microservices.catalog.domain.model.entity.Product;
import es.technical.test.microservices.catalog.openapi.model.ApplicablePriceEcommerceRestDtoV1;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@NullMarked
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ApplicablePriceEcommerceRestDtoV1Mapper {



    @Mapping(target = "productId", source = "id")
    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "priceId", source = "applicablePrice.id")
    @Mapping(target = "price", source = "applicablePrice.amount")
    @Mapping(target = "currency", source = "applicablePrice.currency")
    @Mapping(target = "startDate", source = "applicablePrice.startDate")
    @Mapping(target = "endDate", source = "applicablePrice.endDate")
    @Nullable ApplicablePriceEcommerceRestDtoV1 map(@Nullable Product product);

    default @Nullable OffsetDateTime map(@Nullable Instant instant) {
        if (instant == null) return null;
        return OffsetDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

}
