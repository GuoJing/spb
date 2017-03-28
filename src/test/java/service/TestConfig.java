package service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

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
}
