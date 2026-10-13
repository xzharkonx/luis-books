package com.luis.luis_books.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class DevSecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(auth -> auth
                        // Consola H2 en desarrollo
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        // GET publico
                        .requestMatchers(HttpMethod.GET, "/v1/books").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/categories").permitAll()
                        // GET publico
                        .requestMatchers(HttpMethod.GET, "/v1/books/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/categories/*").permitAll()
                        // POST requiere autenticacion
                        .requestMatchers(HttpMethod.POST, "/v1/books").authenticated()
                        .requestMatchers(HttpMethod.POST, "/v1/categories").authenticated()
                        // PUT requiere autenticacion
                        .requestMatchers(HttpMethod.PUT, "/v1/books/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/v1/categories/{id}").authenticated()
                        // DELETE requiere autenticacion
                        //.requestMatchers(HttpMethod.DELETE, "/v1/books").authenticated()
                        // DELETE requiere autenticacion
                        .requestMatchers(HttpMethod.DELETE, "/v1/books/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/v1/categories/{id}").authenticated()
                        // Proteger otros endpoints
                        .anyRequest().authenticated()
                )
                // Excluir solamente H2 de CSRF
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                PathRequest.toH2Console()
                        )
                        .ignoringRequestMatchers(
                                "/v1/books",
                                "/v1/books/**","/v1/categories",
                                "/v1/categories/**")
                )
                // Permitir frames del mismo origen
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                )

                // Autenticacion desde Postman
                // Se agrega si queremos seguridad con el token que se genera en la consola.
                .httpBasic(Customizer.withDefaults())

                // Formulario de login
                //.formLogin(Customizer.withDefaults())
                .formLogin(form -> form
                        .defaultSuccessUrl("/",true)
                        .permitAll()
                )
                // Se agrega para agregar la instancia del usuario que se loguea.
                .userDetailsService(userDetailsService)
                .build();
    }

    // Se crea este bean para instanciar la clase de encriptacion.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
/*
    @Bean
    public UserDetailsService users(){
        UserDetails user = User.builder()
                .username("Luis")
                .password("{noop}zelda1234")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
*/
}
