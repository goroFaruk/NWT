package AdminServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Šahin on 18.3.2017.
 */
@PropertySource({"classpath:application.properties"})
@EnableDiscoveryClient

//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.REGEX, pattern = "AdminServices.voter.*"),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "AdminServices.multiplelogin.*"),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "AdminServices.multipleentrypoints.*") })
@ComponentScan("AdminServices.multiplelogin")
public class AdminApplication {
    public static void main(String[] args) {
        Logger log= LoggerFactory.getLogger(AdminApplication.class);
        SpringApplication.run(AdminApplication.class, args);
        log.info("AdminService.Application is started");
    }
}

