package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by guojing on 2017/3/18.
 */

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {
    "config", "controller", "dao", "domain", "dto",
    "exceptions", "model.document", "repository",
    "service"})
public class App {
    /**
     * Spring boot entry point.
     * @param args String[] args
     * @throws Exception Spring boot Exception
     */
    public static void main(final String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
