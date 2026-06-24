package ut.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import es.technical.test.microservices.catalog.domain.model.entity.Price;
import es.technical.test.microservices.catalog.domain.model.entity.Product;
import es.technical.test.microservices.catalog.domain.repository.PriceRepository;
import es.technical.test.microservices.catalog.domain.service.ProductServiceImpl;
import es.technical.test.microservices.catalog.domain.service.query.FindApplicablePriceQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private FindApplicablePriceQuery query;
    private Price mockPrice;

    @BeforeEach
    void setUp() {
        query = new FindApplicablePriceQuery(1L, 2L, Instant.ofEpochSecond(1782287299));
        PodamFactory podamFactory = new PodamFactoryImpl();
        mockPrice = podamFactory.manufacturePojo(Price.class);
    }

    @Test
    void findWithApplicablePriceBy_WhenPricesExist_ShouldReturnProductWithPrices() {
        // Arrange
        List<Price> expectedPrices = List.of(mockPrice);
        when(priceRepository.findAllBy(query)).thenReturn(expectedPrices);

        // Act
        Optional<Product> result = productService.findWithApplicablePriceBy(query);

        // Assert
        assertTrue(result.isPresent());
        Product product = result.get();

        assertEquals(query.productId(), product.getId());
        assertNotNull(product.getBrand());
        assertEquals(query.brandId(), product.getBrand().id());
        assertEquals(expectedPrices, product.getPrices());

        verify(priceRepository).findAllBy(query);
    }

    @Test
    void findWithApplicablePriceBy_WhenNoPricesExist_ShouldReturnEmptyOptional() {
        // Arrange
        when(priceRepository.findAllBy(query)).thenReturn(Collections.emptyList());

        // Act
        Optional<Product> result = productService.findWithApplicablePriceBy(query);

        // Assert
        assertFalse(result.isPresent());
        verify(priceRepository).findAllBy(query);
    }
}
