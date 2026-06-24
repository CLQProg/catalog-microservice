package it;

import es.technical.test.microservices.catalog.CatalogApplication;
import io.karatelabs.core.Runner;
import io.karatelabs.core.SuiteResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {CatalogApplication.class})
@ActiveProfiles("test")
class KarateTests {

    @Test
    void testAll() {
        SuiteResult result = Runner.path("classpath:prices/features/prices.feature")
                .outputHtmlReport(true)
                .parallel(1);
        assertTrue(result.isPassed());
    }

}