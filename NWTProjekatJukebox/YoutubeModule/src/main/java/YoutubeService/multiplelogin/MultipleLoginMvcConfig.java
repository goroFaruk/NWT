package YoutubeService.multiplelogin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.context.annotation.ComponentScan;

@EnableWebMvc
@Configuration
public class MultipleLoginMvcConfig extends WebMvcConfigurerAdapter {

    public MultipleLoginMvcConfig() {
        super();
    }

    // API

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        super.addViewControllers(registry);

        registry.addViewController("/anonymous.html");

        registry.addViewController("/login.html");
        registry.addViewController("/homepage.html");
        registry.addViewController("/console.html");
    }

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/view/");
        bean.setSuffix(".jsp");

        return bean;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}