package io.pillopl.library.catalogue;

import io.pillopl.library.commons.events.DomainEvents;
import io.pillopl.library.commons.events.publisher.DomainEventsConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Slf4j
@EnableAutoConfiguration
@Import({CatalogueDatabaseConfig.class, DomainEventsConfig.class, CatalogueController.class })
@PropertySource("classpath:catalogue.properties")
public class CatalogueConfiguration {

    @Bean
    Catalogue catalogue(CatalogueDatabase catalogueDatabase, DomainEvents domainEvents) {
        return new Catalogue(catalogueDatabase, domainEvents);
    }

    @Bean
    CatalogueDatabase catalogueDatabase(JdbcTemplate jdbcTemplate) {
        return new CatalogueDatabase(jdbcTemplate);
    }

    @Profile("local")
    @Bean
    CommandLineRunner init(Catalogue catalogue) {
		log.info("Created book: {}", "0321125215");
		log.info("Created book instance: {}", "0321125215, instance 1");
        return args -> {
            catalogue.addBook("Joshua Bloch", "Effective Java", "0321125215").get();
            catalogue.addBookInstance("0321125215", BookType.Restricted).get();
        };
    }
}
