package com.blog.securingWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import java.util.List;
import java.util.Properties;

import com.blog.Repositories.UserRepository;

@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)

// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

// 	@Autowired
//     UserRepository userRepository;

//     // @Bean
// 	// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// 	// 	http
// 	// 		.authorizeHttpRequests((requests) -> requests
// 	// 			.antMatchers("/login", "/register").permitAll()
// 	// 			.anyRequest().authenticated()
// 	// 		)
// 	// 		.formLogin((form) -> form
// 	// 			.loginPage("/login")
// 	// 			.permitAll()
// 	// 		)
// 	// 		.logout((logout) -> logout.permitAll());

// 	// 	return http.build();
// 	// }

// 	// @Bean
// 	// public UserDetailsService userDetailsService() {
// 	// 	List<com.blog.Models.User> users = userRepository.findAll();

// 	// 	UserDetails[] userDetails = new UserDetails[users.size()];

// 	// 	for (com.blog.Models.User user : users) {
// 	// 		userDetails[users.indexOf(user)] = User.withDefaultPasswordEncoder().username(user.getUsername()).password(user.getPassword()).roles("USER").build();
// 	// 	}

// 	// 	return new InMemoryUserDetailsManager(userDetails);
// 	// }

// 	@Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(inMemoryUserDetailsManager());
//     }

// 	@Bean
//     public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//         final Properties users = new Properties();
//         users.put("user","pass,ROLE_USER,enabled"); //add whatever other user you need
//         return new InMemoryUserDetailsManager(users);
//     }
    
// }

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/login", "/register").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inMemoryUserDetailsManager());
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		List<com.blog.Models.User> users = userRepository.findAll();

		UserDetails[] userDetails = new UserDetails[users.size()];

		for (com.blog.Models.User user : users) {
			userDetails[users.indexOf(user)] = User.withDefaultPasswordEncoder().username(user.getUsername()).password(user.getPassword()).roles("USER").build();
		}

		return new InMemoryUserDetailsManager(userDetails);
    }

}
