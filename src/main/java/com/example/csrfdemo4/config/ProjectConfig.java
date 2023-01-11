package com.example.csrfdemo4.config;

import com.example.csrfdemo4.dao.CustomCsrfTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
public class ProjectConfig {

    @Autowired
    private CustomCsrfTokenRepository customCsrfTokenRepository;
    @Bean
    public CsrfTokenRepository customTokenRepository(){
        return new CustomCsrfTokenRepository();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        var uds = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("mary")
                .password("12345")
                .authorities("READ")
                .build();
        uds.createUser(user1);

        return uds;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Throwable {

        CsrfTokenRequestAttributeHandler handler = new CsrfTokenRequestAttributeHandler();
        handler.setCsrfRequestAttributeName(null);
        http.csrf(c -> {
            c.csrfTokenRequestHandler(handler);
            c.csrfTokenRepository(customTokenRepository());
        }).authorizeHttpRequests()
                .anyRequest()
                .permitAll();
        return http.build();
//        http.authorizeHttpRequests()
//                        .anyRequest().permitAll();
//        http.formLogin()
//                        .defaultSuccessUrl("/main",true);
//
//        http.csrf(c -> {
//                    c.csrfTokenRepository(customCsrfTokenRepository);
//                    c.ignoringRequestMatchers("/product/hello");
//                })
//        http.csrf(
//                c ->
//                );
//                .httpBasic();

    }
}
