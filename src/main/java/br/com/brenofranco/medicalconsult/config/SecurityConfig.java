package br.com.brenofranco.medicalconsult.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**",
            "swagger-ui/**",
            "/v3/api-docs/**"
    };

    private static final String[] PUBLIC_MATCHERS_GET = {
            "/usuarios/**",
            "/consultas/**"
    };

    private static final String[] PUBLIC_MATCHERS_POST = {
            "/usuarios/**",
            "/consultas/**"
    };

    private static final String[] PUBLIC_MATCHERS_PUT = {
            "/usuarios/**",
            "/consultas/**"
    };

    private static final String[] PUBLIC_MATCHERS_DELETE = {
            "/usuarios/**",
            "/consultas/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .headers(h -> h.frameOptions().disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).hasAnyRole("ADMIN", "SECRETARIO")
                        .requestMatchers(HttpMethod.PUT, PUBLIC_MATCHERS_PUT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, PUBLIC_MATCHERS_DELETE).hasAnyRole("ADMIN", "SECRETARIO")
                        .requestMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                        .requestMatchers(HttpMethod.GET, PUBLIC_MATCHERS).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser("BRENO").password(passwordEncoder.encode("12345")).roles("ADMIN")
                .and()
                .withUser("JOAO").password(passwordEncoder.encode("98765")).roles("PACIENTE")
                .and()
                .withUser("IURI").password(passwordEncoder.encode("121212")).roles("SECRETARIA");

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
