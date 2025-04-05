package com.awash.project.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.awash.project.services.AuthUserDetailsService;



@Configuration
@EnableWebSecurity

public class SecurityConfig {
	
	@Autowired
    private AuthUserDetailsService userDetailsService;
	
	@Bean
	public JwtAuthenticationFilter jwtRequestFilter() {
	    return new JwtAuthenticationFilter();
	}
	
	
	// Inject LDAP properties
    
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
	return 
			http
			.cors(cors -> cors.configurationSource(corsConfigurationSource())) 
			.csrf(csrf -> csrf.disable())
			/*.formLogin(httpForm->{
				httpForm
				.loginPage("/login").permitAll();
			})*/
			.authorizeHttpRequests(authorize -> authorize 
					.requestMatchers("/login","/auth/**").permitAll()
					 .requestMatchers("/js/**","/swagger-ui/**","/v3/api-docs/**").permitAll() //"/users/**",
					 .requestMatchers("/admin/**").hasRole("ADMIN")
			            .anyRequest().authenticated()
			            .and()
			            .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class)
			            )
					//.formLogin().disable()
			
		        .logout(logout -> logout
		            .permitAll() // Allow everyone to log out
		        )
			
			.build();	
	}
	
	
	 @Bean
	    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
	        AuthenticationManagerBuilder authenticationManagerBuilder = 
	            http.getSharedObject(AuthenticationManagerBuilder.class);
	      
	        
	        
	     
	         authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	     
	        
	        
	        return authenticationManagerBuilder.build();
	    }
	
	 
	 @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://10.39.30.127:4200"));
	        //configuration.setAllowedOrigins(Arrays.asList("*")); // Allow frontend URL
	        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Allow specific HTTP methods
	        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept")); // Allow specific headers
	        configuration.setAllowCredentials(true);  // Allow cookies to be sent
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
	
	
}
