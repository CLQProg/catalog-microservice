package es.technical.test.microservices.catalog.domain.model.entity;


import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Brand of a product.
 */
@NullMarked
public record Brand(
        @Nullable Long id,
        @Nullable String name
) {

}
