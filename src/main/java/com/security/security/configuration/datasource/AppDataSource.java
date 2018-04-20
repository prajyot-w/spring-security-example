package com.security.security.configuration.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


/**
 * @author prajyot on 20/4/18.
 * @project security.
 */
@Configuration
public class AppDataSource {

    /**
     * Configure all your datasources here.
     *
     * First Create DataSource objects for each
     * db connection mannually and then create JDBC templates for
     * the same.
     *
     * */

    @Autowired
    private Environment env;

    @Bean(name = "MySqlDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mySqlDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;
    }

    @Bean
    public JdbcTemplate mySqlJdbcTemplate(){
        return new JdbcTemplate(mySqlDataSource());
    }

    @Bean(name = "PGSqlDataSource")
    @ConfigurationProperties(prefix = "app.datasource")
    public DataSource pgSqlDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("app.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("app.datasource.url"));
        dataSource.setUsername(env.getProperty("app.datasource.username"));
        dataSource.setPassword(env.getProperty("app.datasource.password"));

        return dataSource;
    }

    @Bean
    @Qualifier("pgJdbcTemplate")
    public JdbcTemplate pgJdbcTemplate(){
        return new JdbcTemplate(pgSqlDataSource());
    }
}
