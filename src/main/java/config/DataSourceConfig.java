package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by guojing on 2017/3/18.
 */
@Configuration
public class DataSourceConfig {
    /**
     * db username config.
     */
    @Value("${db.username}")
    private String username;

    /**
     * db password config.
     */
    @Value("${db.password}")
    private String password;

    /**
     * db dsn config.
     */
    @Value("${db.dsn}")
    private String dsn;

    /**
     * spring datasource driver class name.
     */
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    /**
     * spring datasource auto commit config.
     */
    @Value("${spring.datasource.auto-commit}")
    private boolean autoCommit;

    /**
     * spring datasource connection test query.
     */
    @Value("${spring.datasource.connection-test-query}")
    private String connectionTestQuery;

    /**
     * hikari config connection pool.
     * @return config object
     */
    private HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setAutoCommit(autoCommit);
        config.setConnectionTestQuery(connectionTestQuery);
        config.setJdbcUrl(this.dsn);
        config.setUsername(this.username);
        config.setPassword(this.password);
        return config;
    }

    /**
     * data source.
     * @return new hikari data source
     */
    @Bean(name = "spbDataSource")
    @Primary
    private DataSource spbDataSource() {
        return new HikariDataSource(hikariConfig());
    }

    /**
     * mybatis sql session.
     * @param dataSource the data source object
     * @return a new sql session
     * @throws Exception as exception
     */
    @Bean(name = "spbSqlSession")
    @Primary
    public final SqlSessionFactory sqlSessionFactory(
            @Qualifier("spbDataSource") final DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver;
        resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources(
                "classpath:mapper/*.xml"));
        return sessionFactory.getObject();
    }

    /**
     * transaction manager.
     * @return new data source transaction manager
     */
    @Bean(name = "spbTransactionManager")
    @Primary
    public final DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(spbDataSource());
    }
}
