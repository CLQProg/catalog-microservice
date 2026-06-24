package ut.application.ecommerce.rest.api;

import es.technical.test.microservices.catalog.application.ecommerce.rest.api.PricesEcommerceV1ApiDelegateImpl;
import es.technical.test.microservices.catalog.application.ecommerce.rest.mapper.ApplicablePriceEcommerceRestDtoV1Mapper;
import es.technical.test.microservices.catalog.domain.model.entity.Product;
import es.technical.test.microservices.catalog.domain.model.exception.NotFoundException;
import es.technical.test.microservices.catalog.domain.service.ProductService;
import es.technical.test.microservices.catalog.domain.service.query.FindApplicablePriceQuery;
import es.technical.test.microservices.catalog.openapi.model.ApplicablePriceEcommerceRestDtoV1;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PricesEcommerceV1ApiDelegateImplTest {

    @Mock
    private ProductService productService;

    @Mock
    private ApplicablePriceEcommerceRestDtoV1Mapper applicablePriceEcommerceRestDtoV1Mapper;

    @Mock
    private HttpServletRequest httpServletRequest;

    @InjectMocks
    private PricesEcommerceV1ApiDelegateImpl apiDelegate;

    @Captor
    private ArgumentCaptor<FindApplicablePriceQuery> queryCaptor;

    private Long brandId;
    private Long productId;
    private OffsetDateTime offsetDateTime;
    private Product mockProduct;
    private ApplicablePriceEcommerceRestDtoV1 mockDto;

    @BeforeEach
    void setUp() {
        brandId = 1L;
        productId = 2L;
        offsetDateTime = OffsetDateTime.parse("2026-06-24T10:00:00+02:00");
        PodamFactory podamFactory = new PodamFactoryImpl();
        mockProduct = podamFactory.manufacturePojo(Product.class);
        mockDto = podamFactory.manufacturePojo(ApplicablePriceEcommerceRestDtoV1.class);
    }

    @Test
    void findProductApplicablePriceByBrandIdAndDate_WhenProductExists_ShouldReturnOkWithDto() {
        // Arrange
        when(productService.findWithApplicablePriceBy(any(FindApplicablePriceQuery.class)))
                .thenReturn(Optional.of(mockProduct));
        when(applicablePriceEcommerceRestDtoV1Mapper.map(mockProduct))
                .thenReturn(mockDto);

        // Act
        ResponseEntity<ApplicablePriceEcommerceRestDtoV1> response =
                apiDelegate.findProductApplicablePriceByBrandIdAndDate(brandId, productId, offsetDateTime, httpServletRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDto, response.getBody());

        verify(productService).findWithApplicablePriceBy(queryCaptor.capture());
        FindApplicablePriceQuery capturedQuery = queryCaptor.getValue();
        assertEquals(brandId, capturedQuery.brandId());
        assertEquals(productId, capturedQuery.productId());
        assertEquals(offsetDateTime.toInstant(), capturedQuery.instant());
    }

    @Test
    void findProductApplicablePriceByBrandIdAndDate_WhenDateIsNull_ShouldMapQueryWithNullDate() {
        // Arrange
        when(productService.findWithApplicablePriceBy(any(FindApplicablePriceQuery.class)))
                .thenReturn(Optional.of(mockProduct));
        when(applicablePriceEcommerceRestDtoV1Mapper.map(mockProduct))
                .thenReturn(mockDto);

        // Act
        ResponseEntity<ApplicablePriceEcommerceRestDtoV1> response =
                apiDelegate.findProductApplicablePriceByBrandIdAndDate(brandId, productId, null, httpServletRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(productService).findWithApplicablePriceBy(queryCaptor.capture());
        FindApplicablePriceQuery capturedQuery = queryCaptor.getValue();
        assertEquals(brandId, capturedQuery.brandId());
        assertEquals(productId, capturedQuery.productId());
        assertNotNull(capturedQuery.instant());
    }

    @Test
    void findProductApplicablePriceByBrandIdAndDate_WhenProductDoesNotExist_ShouldThrowNotFoundException() {
        // Arrange
        when(productService.findWithApplicablePriceBy(any(FindApplicablePriceQuery.class)))
                .thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                apiDelegate.findProductApplicablePriceByBrandIdAndDate(brandId, productId, offsetDateTime, httpServletRequest)
        );

        // Assert
        assertEquals(3850239, exception.getErrorCode());
        assertEquals("Price not found", exception.getMessage());
    }
}
