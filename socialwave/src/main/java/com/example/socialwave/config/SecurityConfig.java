package com.example.socialwave.config;

import com.example.socialwave.security.AuthTokenFilter;
import com.example.socialwave.security.AuthenticationEntryPointJwt;
import com.example.socialwave.security.CustomUserDetailsService;
import com.example.socialwave.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    CustomUserDetailsService userDetailsService;

    AuthenticationEntryPointJwt unauthorizedHandler;
    AuthenticationService authenticationService;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/authentication/login", "/api/v1/authentication/signup").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/authentication/refresh-token", "/api/v1/authentication/logout").authenticated()
                .antMatchers(HttpMethod.GET, "/api/v1/users", "/api/v1/users/{id}").authenticated()
                .antMatchers(HttpMethod.POST, "/api/v1/users").authenticated()
                .antMatchers(HttpMethod.GET, "/api/v1/friends").authenticated()
                .antMatchers(HttpMethod.POST, "/api/v1/friends/request/{user_id}").authenticated()
                .antMatchers(HttpMethod.POST, "/api/v1/conversations").authenticated()
//                .antMatchers(HttpMethod.GET, "/conversations").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/v1/conversations/{id}").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/v1/conversations/{id}").authenticated()
                .antMatchers(HttpMethod.POST, "/api/v1/messages").authenticated()
                .antMatchers(HttpMethod.GET, "/api/v1/messages").authenticated()

                .anyRequest().permitAll()
                .and()
                .httpBasic()
                .and()
                .headers().frameOptions().sameOrigin();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
