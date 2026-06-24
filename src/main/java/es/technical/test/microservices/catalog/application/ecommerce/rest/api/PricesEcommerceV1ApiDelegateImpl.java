package es.technical.test.microservices.catalog.application.ecommerce.rest.api;

import es.technical.test.microservices.catalog.application.ecommerce.rest.mapper.ApplicablePriceEcommerceRestDtoV1Mapper;
import es.technical.test.microservices.catalog.domain.model.entity.Product;
import es.technical.test.microservices.catalog.domain.model.exception.NotFoundException;
import es.technical.test.microservices.catalog.domain.service.ProductService;
import es.technical.test.microservices.catalog.domain.service.query.FindApplicablePriceQuery;
import es.technical.test.microservices.catalog.openapi.api.PricesEcommerceV1ApiDelegate;
import es.technical.test.microservices.catalog.openapi.model.ApplicablePriceEcommerceRestDtoV1;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Optional;

/**
 * Price API delegate implementation.
 */
@Slf4j
@RequiredArgsConstructor
@NullMarked
@Component
public class PricesEcommerceV1ApiDelegateImpl implements PricesEcommerceV1ApiDelegate {



    private final ProductService productService;
    private final ApplicablePriceEcommerceRestDtoV1Mapper applicablePriceEcommerceRestDtoV1Mapper;



    /**
     * Find information on the applicable amount of a product based on the group&#39;s brand and an application date.
     *
     * @param brandId   Brand ID (required)
     * @param productId Product ID (required)
     * @param date      Date of application of the product&#39;s amount (required)
     * @return {@link ResponseEntity} of {@link ApplicablePriceEcommerceRestDtoV1}
     */
    @Override
    public ResponseEntity<ApplicablePriceEcommerceRestDtoV1> findProductApplicablePriceByBrandIdAndDate(Long brandId,
                                                                                                        Long productId,
                                                                                                        @Nullable OffsetDateTime date,
                                                                                                        HttpServletRequest ignored) {
        log.info("Searching amount with brand ID {}, product ID {} and date {}", brandId, productId, date);
        FindApplicablePriceQuery query = new FindApplicablePriceQuery(brandId, productId, date != null
                ? date.toInstant()
                : null);
        Optional<Product> optionalProduct = productService.findWithApplicablePriceBy(query);
        if (optionalProduct.isEmpty()) {
            log.info("Price with brand ID {}, product ID {} and date {} not found", brandId, productId, date);
            throw new NotFoundException(3850239, "Price not found");
        }
        log.info("Returning Price with brand ID {}, product ID {} and date {}", brandId, productId, date);
        ApplicablePriceEcommerceRestDtoV1 dto = applicablePriceEcommerceRestDtoV1Mapper.map(optionalProduct.get());
        return ResponseEntity.ok(dto);
    }

}
