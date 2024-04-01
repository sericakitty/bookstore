package hh.sof03.bookstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig  {

  @Autowired
  private UserDetailsService userDetailsService;	

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http
    .authorizeHttpRequests(authorize -> authorize
    .requestMatchers(antMatcher("/css/**")).permitAll() // Enable css when logged out
    .requestMatchers(antMatcher("/signup")).permitAll()
    .requestMatchers(antMatcher("/saveuser")).permitAll()
    .requestMatchers(antMatcher(HttpMethod.GET, "/api/books")).permitAll()
    .requestMatchers(antMatcher(HttpMethod.GET, "/api/books/*")).permitAll()
    .requestMatchers(antMatcher(HttpMethod.DELETE, "/api/books/*")).hasAuthority("ADMIN")
    .anyRequest().authenticated()
    )
      .formLogin(formLogin -> formLogin
        .loginPage("/login")
        .defaultSuccessUrl("/booklist", true)
        .permitAll()
      )
      .logout(logout -> logout.permitAll());
    return http.build();
  }

   @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
  }
}
