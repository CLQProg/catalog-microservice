package ut.infrastructure.adapter.jpa.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import es.technical.test.microservices.catalog.domain.model.entity.Price;
import es.technical.test.microservices.catalog.infrastructure.adapter.jpa.entity.PriceDbEntity;
import es.technical.test.microservices.catalog.infrastructure.adapter.jpa.mapper.PriceJpaMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class PriceJpaMapperTest {

    @SuppressWarnings("all")
    private final PriceJpaMapper mapper = Mappers.getMapper(PriceJpaMapper.class);

    @Test
    void map_WhenDbEntityIsValid_ShouldMapAllFieldsCorrectlyIncludingTypeConversion() {
        // Arrange
        Instant startDate = Instant.parse("2026-06-24T00:00:00Z");
        Instant endDate = Instant.parse("2026-06-24T23:59:59Z");

        PriceDbEntity dbEntity = new PriceDbEntity(
                100L,
                startDate,
                endDate,
                5L,
                29.99,
                "EUR",
                200L,
                300L
        );

        // Act
        Price price = mapper.map(dbEntity);

        // Assert
        assertNotNull(price);
        assertEquals(100L, price.id());
        assertEquals(startDate, price.startDate());
        assertEquals(endDate, price.endDate());

        assertEquals(5, price.priority());

        assertEquals(29.99, price.amount());
        assertEquals("EUR", price.currency());
    }

    @Test
    void map_WhenDbEntityIsNull_ShouldReturnNull() {
        // Act
        Price price = mapper.map((PriceDbEntity) null);

        // Assert
        assertNull(price);
    }

    @Test
    void map_WhenDbEntityHasNullFields_ShouldMapToNullsInRecord() {
        // Arrange
        PriceDbEntity dbEntity = new PriceDbEntity(
                null, null, null, null, null, null, 200L, 300L
        );

        // Act
        Price price = mapper.map(dbEntity);

        // Assert
        assertNotNull(price);
        assertNull(price.id());
        assertNull(price.startDate());
        assertNull(price.endDate());
        assertNull(price.priority());
        assertNull(price.amount());
        assertNull(price.currency());
    }

    @Test
    void mapList_WhenDbEntitiesListIsValid_ShouldReturnPricesList() {
        // Arrange
        PriceDbEntity dbEntity = new PriceDbEntity(
                1L,
                Instant.ofEpochSecond(1782287299),
                Instant.ofEpochSecond(1782287299),
                1L,
                10.0,
                "USD",
                10L,
                20L
        );
        List<PriceDbEntity> entities = List.of(dbEntity);

        // Act
        List<Price> prices = mapper.map(entities);

        // Assert
        assertNotNull(prices);
        assertEquals(1, prices.size());
        assertEquals(1L, prices.getFirst().id());
    }

    @Test
    void mapList_WhenDbEntitiesListIsNull_ShouldReturnNull() {
        // Act
        List<Price> prices = mapper.map((List<PriceDbEntity>) null);

        // Assert
        assertNull(prices);
    }

    @Test
    void mapList_WhenDbEntitiesListIsEmpty_ShouldReturnEmptyList() {
        // Act
        List<Price> prices = mapper.map(Collections.emptyList());

        // Assert
        assertNotNull(prices);
        assertTrue(prices.isEmpty());
    }
}
