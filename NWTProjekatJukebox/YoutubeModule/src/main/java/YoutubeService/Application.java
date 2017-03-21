package YoutubeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Šahin on 18.3.2017.
 */
@Configuration
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        Logger log= LoggerFactory.getLogger(Application.class);
        SpringApplication.run(Application.class, args);
        log.info("YoutubeService.Application is started");
    }
}
