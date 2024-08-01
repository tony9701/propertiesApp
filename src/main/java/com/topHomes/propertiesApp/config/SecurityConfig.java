package com.topHomes.propertiesApp.config;

import com.topHomes.propertiesApp.repository.UserRepository;
import com.topHomes.propertiesApp.service.impl.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        // Setup which URL-s are available to who
                        authorizeRequests ->
                                authorizeRequests
                                        // all static resources to "common locations" (css, images, js) are available to anyone
                                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                        // some more resources for all users
                                        .requestMatchers("/", "/login", "/register", "/user-register", "/agency-register","/error",
                                                "/properties/buy", "/properties/rent", "/properties/", "/contact-us",
                                                "/about-us", "/properties").permitAll()
                                        // all other URL-s should be authenticated.
                                        .requestMatchers("/admin").hasRole("ADMIN")
                                        .requestMatchers("/agencies/agency-panel").hasAnyRole("AGENCY_ADMIN", "AGENT")
                                        .requestMatchers("/agencies/add-agent").hasRole("AGENCY_ADMIN")
                                        .requestMatchers("/agencies/{id}").hasRole("ADMIN")
                                        .requestMatchers("/users/my-properties").hasAnyRole("AGENT", "AGENCY_ADMIN")
                                        .requestMatchers("/users/{id}").hasRole("ADMIN")
                                        .requestMatchers("/properties/add").hasAnyRole("AGENT", "AGENCY_ADMIN")
                                        .anyRequest()
                                        .authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                // Where is our custom login form?
                                .loginPage("/login")
                                // what is the name of the username parameter in the Login POST request?
                                .usernameParameter("email")
                                // what is the name of the password parameter in the Login POST request?
                                .passwordParameter("password")
                                // What will happen if the login is successful
                                .defaultSuccessUrl("/", true)
                                // What will happen if the login fails
                                .failureForwardUrl("/login-error")
                )
                .logout(
                        logout ->
                                logout
                                        // what is the logout URL?
                                        .logoutUrl("/logout")
                                        // Where to go after successful logout?
                                        .logoutSuccessUrl("/")
                                        // invalidate the session after logout.
                                        .invalidateHttpSession(true)
                )
                .build();
    }

    @Bean
    public UserDetailsServiceImpl userDetailsService(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

