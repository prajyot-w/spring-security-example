package com.security.security.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author prajyot on 15/4/18.
 * @project security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
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
    public void configure(WebSecurity web) throws Exception {
        /** User does not need to login to access these elements */
        web
                .ignoring()
                .antMatchers("/css/**/*.css")
                .antMatchers("/js/**/*.js")
                .antMatchers("/bower_components/**/*");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /** Disable csrf */
        http.csrf().disable();

        /** Authorise only admin user to access hello url */
        http
                .authorizeRequests()
                .antMatchers("/service/hello").hasRole("ADMIN")
                .anyRequest().authenticated();

        /** access denied page */
        /*http
                .authorizeRequests()
                .and().exceptionHandling()
                .accessDeniedPage("/403");*/

        /** Form Login page and logout permitted to all */
        http
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(successHandler)
                .permitAll()
                .and()
                .logout().invalidateHttpSession(true).permitAll();
    }
}
