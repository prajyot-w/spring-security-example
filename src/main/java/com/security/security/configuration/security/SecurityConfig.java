package com.security.security.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author prajyot on 15/4/18.
 * @project security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder(){
        /**
         * Setup passcode encoder for passwords*/
        //return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance(); // For No PasswordEncoder
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /** Provide authentication mechanism from database for users
         *  Providing passwordEncoder is mandatory */
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /** Disable csrf */
        http.csrf().disable();

        /** User does not need to login to access these elements */
        http
                .authorizeRequests()
                .antMatchers(
                        "/css/**/*.css",
                        "/js/**/*.js",
                        "/bower_components/**/*")
                .permitAll()
                .anyRequest().authenticated();

        /** Authorise only admin user to access hello url */
        /*http
                .authorizeRequests()
                .antMatchers("/service/hello")
                .hasAuthority("ROLE_ADMIN")
                .anyRequest()
                .permitAll();*/

        /** access denied page */
        http
                .authorizeRequests()
                .and().exceptionHandling()
                .accessDeniedPage("/403");

        /** Form Login page and logout permitted to all */
        http
                .formLogin()
                .loginPage("/login")
                .permitAll().and()
                .logout().permitAll();
    }
}
