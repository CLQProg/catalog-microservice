package es.technical.test.microservices.catalog.infrastructure.config;


import es.technical.test.microservices.catalog.domain.repository.PriceRepository;
import es.technical.test.microservices.catalog.domain.service.ProductService;
import es.technical.test.microservices.catalog.domain.service.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {

    @Bean
    public ProductService priceService(final PriceRepository priceRepository) {
        return new ProductServiceImpl(priceRepository);
    }

}
