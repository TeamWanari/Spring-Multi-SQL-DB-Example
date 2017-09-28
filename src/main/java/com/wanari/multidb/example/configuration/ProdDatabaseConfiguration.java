package com.wanari.multidb.example.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
    basePackages = ProdDatabaseConfiguration.REPOSITORY_PACKAGE,
    entityManagerFactoryRef = ProdDatabaseConfiguration.ENTITY_MANAGER_FACTORY,
    transactionManagerRef = ProdDatabaseConfiguration.TRANSACTION_MANAGER
)
@EnableTransactionManagement
public class ProdDatabaseConfiguration {

    private static final String SPECIFIER = "prod";

    private static final String DOMAIN_PACKAGE = "com.wanari.multidb.example.domain." + SPECIFIER;
    static final String REPOSITORY_PACKAGE = "com.wanari.multidb.example.repository." + SPECIFIER;
    private static final String CONFIGURATION_PROPERTIES = "spring.datasource." + SPECIFIER;

    private static final String DATA_SOURCE = SPECIFIER + "DataSource";
    static final String ENTITY_MANAGER_FACTORY = SPECIFIER + "EntityManagerFactory";
    static final String TRANSACTION_MANAGER = SPECIFIER + "TransactionManager";
    private static final String PERSISTENCE_UNIT = SPECIFIER + "PersistenceUnit";

    private static final String LIQUIBASE_CHANGELOG_MASTER_LOCATION = "classpath:liquibase/" + SPECIFIER + "_master.xml";

    // This is not important, you don't want to modify anyhing on the prod DB
    // It's only to help you running the application :)
    @Bean(name = "prodLiquibase")
    public SpringLiquibase liquibase(@Qualifier(DATA_SOURCE) DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(LIQUIBASE_CHANGELOG_MASTER_LOCATION);
        return liquibase;
    }

    @Bean(name = DATA_SOURCE)
    @ConfigurationProperties(prefix = CONFIGURATION_PROPERTIES)
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier(DATA_SOURCE) DataSource prodDataSource
    ) {
        return builder
            .dataSource(prodDataSource)
            .packages(DOMAIN_PACKAGE)
            .persistenceUnit(PERSISTENCE_UNIT)
            .build();
    }

    @Bean(name = TRANSACTION_MANAGER)
    public PlatformTransactionManager transactionManager(
        @Qualifier(ENTITY_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
