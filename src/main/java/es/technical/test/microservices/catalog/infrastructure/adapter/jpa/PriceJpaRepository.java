package es.technical.test.microservices.catalog.infrastructure.adapter.jpa;


import es.technical.test.microservices.catalog.infrastructure.adapter.jpa.entity.PriceDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * {@link PriceDbEntity} H2 repository.
 */
@Repository
public interface PriceJpaRepository extends JpaRepository<PriceDbEntity, Long>,
        JpaSpecificationExecutor<PriceDbEntity> {

    @Query("SELECT p FROM PriceDbEntity p " +
            "WHERE p.brandEntityId = :brandId " +
            "AND p.productEntityId = :productId " +
            "AND p.startDate < :instant " +
            "AND p.endDate > :instant ")
    List<PriceDbEntity> findByBrandAndProductAndInstant(@Param("brandId") Long brandId,
                                                        @Param("productId") Long productId,
                                                        @Param("instant") Instant instant);

}
