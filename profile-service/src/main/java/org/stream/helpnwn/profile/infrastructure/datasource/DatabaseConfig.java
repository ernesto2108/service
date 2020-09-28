package org.stream.helpnwn.profile.infrastructure.datasource;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableR2dbcRepositories
public class DatabaseConfig extends AbstractR2dbcConfiguration {

    @Value("${spring.r2dbc.username}")
    private String username;

    @Value("${spring.r2dbc.password}")
    private String password;

    @Value("${spring.r2dbc.password}")
    private String name;

    @Bean
    public DatabaseClient r2dbcDatabaseClient() {
        return DatabaseClient.create(connectionFactory());
    }

    @Bean
    public ConnectionFactory connectionFactory() {

        PostgresqlConnectionConfiguration config =
                PostgresqlConnectionConfiguration.builder()
                        .database("profile_service")
                        .username("postgres")
                        .password("postgres")
                        .host("localhost")
                        .build();

        return new PostgresqlConnectionFactory(config);
    }

    @Bean
    public ReactiveTransactionManager transactionManager() {
        return new R2dbcTransactionManager(connectionFactory());
    }

}
