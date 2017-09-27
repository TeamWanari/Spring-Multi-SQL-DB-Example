package com.wanari.multidb.example.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
// Change this if you want to add a subpackage for your repositories
@EnableJpaRepositories("com.wanari.multidb.example.repository")
public class ViewDatabaseConfiguration {

    private static final String NAME = "view";

    private static final String DATA_SOURCE = NAME + "DataSource";
    private static final String CONFIGURATION_PROPERTIES = "spring.datasource." + NAME;
    private static final String ENTITY_MANAGER_FACTORY = NAME + "EntityManagerFactory";
    private static final String DOMAIN_PACKAGE = "com.wanari.multidb.example.domain";
    private static final String PERSISTENCE_UNIT = NAME + "persistenceUnit";
    private static final String TRANSACTION_MANAGER = NAME + "TransactionManager";

    // Unomment this if you want to add a subpackage for your domains
    // private static final String DOMAIN_PACKAGE = "com.wanari.multidb.example.domain." + NAME;

    @Bean
    @Primary
    public SpringLiquibase liquibase(@Qualifier(DATA_SOURCE) DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:liquibase/master.xml");
        return liquibase;
    }

    @Bean(name = DATA_SOURCE)
    @Primary
    @ConfigurationProperties(prefix = CONFIGURATION_PROPERTIES)
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    // Added 'entityManagerFactory' name too, so spring will use this as default
    @Bean(name = {ENTITY_MANAGER_FACTORY, "entityManagerFactory"})
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier(DATA_SOURCE) DataSource dataSource
    ) {
        return builder
            .dataSource(dataSource)
            .packages(DOMAIN_PACKAGE)
            .persistenceUnit(PERSISTENCE_UNIT)
            .build();
    }

    @Bean(name = TRANSACTION_MANAGER)
    @Primary
    public PlatformTransactionManager transactionManager(@Qualifier(ENTITY_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
