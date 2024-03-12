package com.example.microservicio_usuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable() // Asegúrate de configurar CORS correctamente para producción
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/public/**").permitAll() // Rutas accesibles sin autenticación
                .antMatchers("/api/users/**").hasRole("USER")
                .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
                .and()
                .addFilterBefore(customJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public JwtTokenFilter customJwtTokenFilter() {
        return new JwtTokenFilter();
    }
}


