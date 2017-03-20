package config;

import com.github.pagehelper.PageHelper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
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
import java.util.Properties;

/**
 * Created by guojing on 2017/3/18.
 */
@Configuration
public class DataSourceConfig {
    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Value("${db.dsn}")
    private String dsn;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.auto-commit}")
    private boolean autoCommit;

    private HikariConfig hikariConfig(String url, String userName, String password){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setAutoCommit(autoCommit);
        config.setJdbcUrl(url);
        config.setUsername(userName);
        config.setPassword(password);
        return config;
    }

    @Bean(name = "spbDataSource")
    @Primary
    public DataSource spbDataSource() {
        return new HikariDataSource(hikariConfig(dsn, username, password));
    }

    @Bean(name = "spbSqlSession")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("spbDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//
//        properties.setProperty("dialect", "mysql");
//        properties.setProperty("offsetAsPageNum", "true");
//        properties.setProperty("rowBoundsWithCount", "true");
//        properties.setProperty("pageSizeZero", "false");
//        properties.setProperty("reasonable", "false");
//        //properties.setProperty("params", "pageNum=start;pageSize=limit;");
//        pageHelper.setProperties(properties);
//
//        Interceptor[] plugins = new Interceptor[]{pageHelper};
//        bean.setPlugins(plugins);
//
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "spbTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(spbDataSource());
    }
}
