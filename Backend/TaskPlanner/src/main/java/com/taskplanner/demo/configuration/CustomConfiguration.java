package com.taskplanner.demo.configuration;

import java.util.ArrayList;
import java.util.List;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.taskplanner.demo.service.CustomUserDetailsServiceImpl;

@Configuration
public class CustomConfiguration {
	
	
	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
				
				auth -> auth.requestMatchers("/user/welcome/signup", "/user/auth/login").permitAll()				
				.requestMatchers("/task", "/sprint").authenticated()
				).csrf().disable()
		.formLogin()
		.loginPage("/user/auth/login")
		.permitAll()
		.and()
		.httpBasic();
		
		return http.build();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }   
    
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManager();
    }
   

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
    	
    	List<AuthenticationProvider> list = new ArrayList<>();
    	list.add(authenticationProvider());
    	
        return new ProviderManager(list);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
    	return new CustomUserDetailsServiceImpl();
    }
}
