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
//CHECKSTYLE:OFF: checkstyle:finalclass
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
     * new hikari config object.
     * @param mysqlDSN mysql dsn
     * @param mysqlUserName mysql user name
     * @param mysqlPassword mysql password
     * @return config object
     */
    public HikariConfig hikariConfig(
            final String mysqlDSN, final String mysqlUserName,
            final String mysqlPassword
    ) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setAutoCommit(autoCommit);
        config.setConnectionTestQuery(connectionTestQuery);
        config.setJdbcUrl(mysqlDSN);
        config.setUsername(mysqlUserName);
        config.setPassword(mysqlPassword);
        return config;
    }

    /**
     * data source.
     * @return new hikari data source
     */
    @Bean(name = "spbDataSource")
    @Primary
    public DataSource spbDataSource() {
        return new HikariDataSource(hikariConfig(
                dsn, username, password
        ));
    }

    /**
     * mybatis sql session.
     * @param dataSource the data source object
     * @return a new sql session
     * @throws Exception as exception
     */
    @Bean(name = "spbSqlSession")
    @Primary
    public SqlSessionFactory sqlSessionFactory(
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
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(spbDataSource());
    }
}
//CHECKSTYLE:ON: checkstyle:finalclass
