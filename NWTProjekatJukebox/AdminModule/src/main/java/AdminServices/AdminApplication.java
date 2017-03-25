package AdminServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Šahin on 18.3.2017.
 */
@PropertySource({"classpath:application.properties"})
@Configuration
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        Logger log= LoggerFactory.getLogger(AdminApplication.class);
        SpringApplication.run(AdminApplication.class, args);
        log.info("AdminService.Application is started");
    }
}
