package com.gilsonbraggion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		
		http
        .authorizeRequests()
          .antMatchers("/", "/resources/**", "/static/**","/webjars/**").permitAll()
          .anyRequest().authenticated()
          .and()
          .headers().frameOptions().disable()
          .and()
          .csrf().disable()
      .logout()
      	.logoutSuccessUrl("/login")
        .permitAll()
        .and()
		.oauth2Login().defaultSuccessUrl("/user", true);
		
	}
	
	@Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

}
