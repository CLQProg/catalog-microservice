package ut.application.ecommerce.rest.mapper;

import es.technical.test.microservices.catalog.application.ecommerce.rest.mapper.ApplicablePriceEcommerceRestDtoV1Mapper;
import es.technical.test.microservices.catalog.domain.model.entity.Brand;
import es.technical.test.microservices.catalog.domain.model.entity.Price;
import es.technical.test.microservices.catalog.domain.model.entity.Product;
import es.technical.test.microservices.catalog.openapi.model.ApplicablePriceEcommerceRestDtoV1;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicablePriceEcommerceRestDtoV1MapperTest {

    @SuppressWarnings("all")
    private final ApplicablePriceEcommerceRestDtoV1Mapper mapper = Mappers.getMapper(ApplicablePriceEcommerceRestDtoV1Mapper.class);

    @Test
    void map_WhenProductWithPriceAndBrand_ShouldMapAllFieldsCorrectly() {
        // Arrange
        Instant startInstant = Instant.parse("2026-06-24T10:00:00Z");
        Instant endInstant = Instant.parse("2026-06-24T20:00:00Z");

        Brand brand = new Brand(100L, "ZARA");

        Price applicablePrice = Price.builder()
                .id(500L)
                .amount(29.99d)
                .currency("EUR")
                .startDate(startInstant)
                .endDate(endInstant)
                .build();

        Product product = Product.builder()
                .id(1L)
                .brand(brand)
                .prices(List.of(applicablePrice))
                .build();

        // Act
        ApplicablePriceEcommerceRestDtoV1 dto = mapper.map(product);

        // Assert
        assertNotNull(dto);
        assertEquals(1L, dto.getProductId());
        assertEquals(100L, dto.getBrandId());
        assertEquals(500L, dto.getPriceId());
        assertEquals(29.99d, dto.getPrice());
        assertEquals("EUR", dto.getCurrency());

        assertEquals(OffsetDateTime.ofInstant(startInstant, ZoneOffset.UTC), dto.getStartDate());
        assertEquals(OffsetDateTime.ofInstant(endInstant, ZoneOffset.UTC), dto.getEndDate());
    }

    @Test
    void map_WhenProductIsNull_ShouldReturnNull() {
        // Act
        ApplicablePriceEcommerceRestDtoV1 dto = mapper.map((Product) null);

        // Assert
        assertNull(dto);
    }

    @Test
    void mapInstant_WhenInstantIsNull_ShouldReturnNull() {
        // Act
        OffsetDateTime dateTime = mapper.map((Instant) null);

        // Assert
        assertNull(dateTime);
    }

    @Test
    void mapInstant_WhenInstantIsValid_ShouldReturnOffsetDateTimeInUTC() {
        // Arrange
        Instant now = Instant.ofEpochSecond(1782287299);

        // Act
        OffsetDateTime dateTime = mapper.map(now);

        // Assert
        assertNotNull(dateTime);
        assertEquals(ZoneOffset.UTC, dateTime.getOffset());
        assertEquals(now, dateTime.toInstant());
    }
}
