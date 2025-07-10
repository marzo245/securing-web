package com.example.securing_web.securingweb;

import com.example.securing_web.model.Model;
import com.example.securing_web.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
    http
      .authorizeHttpRequests(requests ->
        requests
          .requestMatchers("/", "/home")
          .permitAll()
          .anyRequest()
          .authenticated()
      )
      .formLogin(form -> form
        .loginPage("/login").permitAll()
        .defaultSuccessUrl("/hello", true)
      )
      .logout(logout -> logout.permitAll());

    return http.build();
  }

  @Autowired
  private MyRepository myRepository;

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> {
      Model usuario = myRepository.findByUsername(username);
      if (usuario == null) {
        throw new UsernameNotFoundException("Usuario no encontrado");
      }
      return User
        .withUsername(usuario.getUsername())
        .password(usuario.getPassword())
        .roles(usuario.getRole())
        .build();
    };
  }
}
