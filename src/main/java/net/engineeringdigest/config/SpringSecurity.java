package net.engineeringdigest.config;

import net.engineeringdigest.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/api/journal/**", "/api/user/**").authenticated()
                .antMatchers("/api/admin/**").hasRole("admin")
                .anyRequest().permitAll()
                .and()
                .httpBasic();
    http.csrf().disable();
    }
    @Autowired
   private UserDetailsService uds;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uds).passwordEncoder(Bcrypt());
    }

    @Bean
    public static PasswordEncoder Bcrypt(){
        return new BCryptPasswordEncoder();
    }
}
