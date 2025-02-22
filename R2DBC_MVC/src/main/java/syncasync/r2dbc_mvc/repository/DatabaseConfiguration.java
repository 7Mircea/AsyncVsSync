package syncasync.r2dbc_mvc.repository;

import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.transaction.ReactiveTransactionManager;

import static io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;

import java.time.ZoneId;

@Configuration
@EnableR2dbcRepositories
class DatabaseConfiguration extends AbstractR2dbcConfiguration {

    @Value("${spring.r2dbc.username}")
    String username;

    @Value("${spring.r2dbc.password}")
    String password;

    @Value("${spring.r2dbc.max-size}")
    String maxSize;

    @Value("${spring.r2dbc.url}")
    String url;

    @Value("${init}")
    Boolean init;

    private static final String DB_PROTOCOL = "postgresql";
    private static final String DB_DRIVER = "pool";
    private static final String ZONE_ID = "UTC+2";
    private final int maxClientConnections = 1000;

    @Bean
    public ConnectionFactory connectionFactory() {
        R2DBCURLSplitter myUrl = new R2DBCURLSplitter(url);
        System.out.println(myUrl);
        String host = myUrl.getHost();
        Integer port = myUrl.getPort();
        String database = myUrl.getDatabase();

        return ConnectionFactoryBuilder.withOptions(ConnectionFactoryOptions.builder()
                .option(DRIVER, DB_DRIVER)
                .option(PROTOCOL, DB_PROTOCOL)
                .option(MAX_SIZE, Integer.valueOf(maxSize))
                .option(HOST, host)
                .option(PORT, port)
                .option(USER, username)
                .option(PASSWORD, password)
                .option(DATABASE, database)
                //.option(SERVER_ZONE_ID, ZoneId.of(ZONE_ID))
        ).build();

    }

    @Bean
    ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        if (init) {
            CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
            ClassPathResource cpr = new ClassPathResource("schema._sql");
            if (cpr.exists()) {
                populator.addPopulators(new ResourceDatabasePopulator(cpr));
            }
            initializer.setDatabasePopulator(populator);
        }

        return initializer;
    }

}