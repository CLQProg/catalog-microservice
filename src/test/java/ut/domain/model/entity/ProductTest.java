package ut.domain.model.entity;

import es.technical.test.microservices.catalog.domain.model.entity.Brand;
import es.technical.test.microservices.catalog.domain.model.entity.Price;
import es.technical.test.microservices.catalog.domain.model.entity.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void builder_WhenPricesListIsNull_ShouldInitializeEmptyPricesAndNullApplicablePrice() {
        // Act
        Product product = Product.builder()
                .id(1L)
                .brand(new Brand(10L, "Brand Name"))
                .prices(null)
                .build();

        // Assert
        assertEquals(1L, product.getId());
        assertNotNull(product.getBrand());
        assertNotNull(product.getPrices());
        assertTrue(product.getPrices().isEmpty());
        assertNull(product.getApplicablePrice());
    }

    @Test
    void builder_WhenPricesListIsEmpty_ShouldHaveEmptyPricesAndNullApplicablePrice() {
        // Act
        Product product = Product.builder()
                .id(1L)
                .prices(new ArrayList<>())
                .build();

        // Assert
        assertTrue(product.getPrices().isEmpty());
        assertNull(product.getApplicablePrice());
    }

    @Test
    void builder_WhenPricesHaveDifferentPriorities_ShouldSelectPriceWithMaxPriority() {
        // Arrange
        Price lowPriorityPrice = Price.builder().id(1L).priority(1).build();
        Price highPriorityPrice = Price.builder().id(2L).priority(5).build();
        Price mediumPriorityPrice = Price.builder().id(3L).priority(3).build();

        List<Price> prices = Arrays.asList(lowPriorityPrice, highPriorityPrice, mediumPriorityPrice);

        // Act
        Product product = Product.builder()
                .id(1L)
                .prices(prices)
                .build();

        // Assert
        assertNotNull(product.getApplicablePrice());
        assertEquals(5, product.getApplicablePrice().priority());
        assertEquals(2L, product.getApplicablePrice().id());
    }

    @Test
    void builder_WhenPricesHaveNullPriorities_ShouldHandleNullsWithoutThrowingException() {
        // Arrange
        Price nullPriorityPrice1 = Price.builder().id(1L).priority(null).build();
        Price nullPriorityPrice2 = Price.builder().id(2L).priority(null).build();
        Price validPriorityPrice = Price.builder().id(3L).priority(0).build();

        List<Price> prices = Arrays.asList(nullPriorityPrice1, nullPriorityPrice2, validPriorityPrice);

        // Act
        Product product = Product.builder()
                .id(1L)
                .prices(prices)
                .build();

        // Assert
        assertNotNull(product.getApplicablePrice());
        assertEquals(0, product.getApplicablePrice().priority());
        assertEquals(3L, product.getApplicablePrice().id());
    }

    @Test
    void builder_WhenAllPricesHaveNullPriorities_ShouldSelectAnyPriceSafely() {
        // Arrange
        Price nullPriorityPrice1 = Price.builder().id(1L).priority(null).build();
        Price nullPriorityPrice2 = Price.builder().id(2L).priority(null).build();

        List<Price> prices = Arrays.asList(nullPriorityPrice1, nullPriorityPrice2);

        // Act
        Product product = Product.builder()
                .id(1L)
                .prices(prices)
                .build();

        // Assert
        assertNotNull(product.getApplicablePrice());
        assertNull(product.getApplicablePrice().priority());
    }
}
