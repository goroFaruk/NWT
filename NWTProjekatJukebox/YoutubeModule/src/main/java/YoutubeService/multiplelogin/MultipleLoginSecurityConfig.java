package YoutubeService.multiplelogin;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.TestingAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class MultipleLoginSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        //mislim da ne radi ovo nista
        manager.createUser(User.withUsername("user").password("userPass").roles("USER").build());
        manager.createUser(User.withUsername("admin").password("adminPass").roles("ADMIN").build());
        return manager;
    }

    @Configuration
    @Order(1)
    public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        public App1ConfigurationAdapter() {
            super();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin*").authorizeRequests().anyRequest().hasRole("ADMIN")
                    // log in
                    .and().formLogin().loginPage("/loginAdmin").loginProcessingUrl("/admin_login").failureUrl("/loginAdmin?error=loginError").defaultSuccessUrl("/adminPage")
                    // logout
                    .and().logout().logoutUrl("/admin_logout").logoutSuccessUrl("/protectedLinks").deleteCookies("JSESSIONID").and().exceptionHandling().accessDeniedPage("/403").and().csrf().disable();
        }
    }

    @Configuration
    @Order(2)
    public static class App2ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private DataSource dataSource;


        public App2ConfigurationAdapter() {
            super();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            //MyUserDetailsService s=new MyUserDetailsService();
            //UserDetails user1= s.loadUserByUsername("john");
            auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(getUserQuery()).authoritiesByUsernameQuery(getAuthoritiesQuery());//authoritiesByUsernameQuery(getAuthoritiesQuery());
            //auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
        }

        protected void configure(HttpSecurity http) throws Exception {
            http.requestMatchers().antMatchers("/user*","/youtube*","/listapjesama*","/lista*").and().authorizeRequests().anyRequest().hasRole("USER")
                    // log in
                    .and().formLogin().loginPage("/loginUser").loginProcessingUrl("/user_login").failureUrl("/loginUser?error=loginError").defaultSuccessUrl("/userPage")
                    // logout
                    .and().logout().logoutUrl("/user_logout").logoutSuccessUrl("/protectedLinks").deleteCookies("JSESSIONID").and().exceptionHandling().accessDeniedPage("/403").and().csrf().disable();
        }

        private String getUserQuery() {
            return "SELECT username, pasword, enabled  "
                    + "FROM user "
                    + "WHERE username = ?";
        }

        private String getAuthoritiesQuery() {
            return "select username, 'ROLE_USER' from user where username = ? ";
        }
    }

}
