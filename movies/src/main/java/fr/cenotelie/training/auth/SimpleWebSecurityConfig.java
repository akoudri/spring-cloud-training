package fr.cenotelie.training.auth;

import org.springframework.context.annotation.Bean;
/*import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;*/

//@Configuration
//@EnableWebSecurity
public class SimpleWebSecurityConfig {

   /* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/api/*").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails details = User.withDefaultPasswordEncoder()
                .username("training")
                .password("training")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(details);
    }*/

}
