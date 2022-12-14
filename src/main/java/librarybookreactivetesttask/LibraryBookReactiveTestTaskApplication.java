package librarybookreactivetesttask;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@EntityScan(basePackages = "librarybookreactivetesttask.model")
@SpringBootApplication(scanBasePackages = "librarybookreactivetesttask", exclude = ErrorWebFluxAutoConfiguration.class)
@EnableR2dbcRepositories(basePackages = "librarybookreactivetesttask.repository")
public class LibraryBookReactiveTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryBookReactiveTestTaskApplication.class, args);
    }

    @Bean
    public ConnectionFactoryInitializer databaseInitializer(ConnectionFactory connectionFactory) {
        final ConnectionFactoryInitializer cfi = new ConnectionFactoryInitializer();
        cfi.setConnectionFactory(connectionFactory);

        final CompositeDatabasePopulator cdp = new CompositeDatabasePopulator();
        cdp.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema/schema.sql")));
        cdp.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema/data.sql")));
        cfi.setDatabasePopulator(cdp);
        return cfi;
    }

}
