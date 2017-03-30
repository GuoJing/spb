package config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

/**
 * Created by guojing on 2017/3/28.
 */
public class TestConfig {
    @SpringBootApplication
    @ComponentScan(basePackages = {"controller", "dao", "config", "domain", "dto", "service"})
    @EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class, EmbeddedServletContainerAutoConfiguration.class})
    public static class DaoConfig {

        @Bean(destroyMethod = "shutdown")
        public EmbeddedDatabase dataSource() {
            return new EmbeddedDatabaseBuilder().
                    setType(EmbeddedDatabaseType.H2).
                    addScript("db.sql").
                    addScript("db-data.sql").
                    build();
        }
    }

    @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
            DataSourceTransactionManagerAutoConfiguration.class,
            EmbeddedServletContainerAutoConfiguration.class})
    @ComponentScan(basePackages = {"controller", "dao", "config", "domain", "dto", "service"},
            excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                    value = {DataSourceConfig.class})})
    public static class ServiceConfig {

        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }

        @Bean
        public MockRestServiceServer mockRestServiceServer() {
            return MockRestServiceServer.createServer(restTemplate());
        }
    }
}
