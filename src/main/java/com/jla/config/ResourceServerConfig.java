package com.jla.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *The @EnableResourceServer annotation adds a filter of type OAuth2AuthenticationProcessingFilter automatically
 *to the Spring Security filter chain.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().
        frameOptions().disable().and().cors().disable().csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/home","/register","/login","/api/**").permitAll().and().
              //  .antMatchers("/api/**","/logout").authenticated().and().
                logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
             
               
    }


}
