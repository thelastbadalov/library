package com.company.config;

import com.company.security.JwtAccessDeniedHandler;
import com.company.security.JwtAuthenticationEntryPoint;
import com.company.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfigure     {
private final JwtFilter jwtFilter;
private final JwtAccessDeniedHandler accessDeniedHandler;
private final JwtAuthenticationEntryPoint entryPoint;

@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
}

@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf().disable()
            .cors().and()
            .authorizeRequests((auth)-> {
                        auth.antMatchers("/api/admin").hasAuthority("ADMIN");
                           auth.antMatchers("/api/user").hasAnyAuthority("ADMIN","USER");
                           auth.antMatchers("/api/public").permitAll();
 auth.anyRequest().authenticated();
                    })
            .formLogin().disable()
            .httpBasic().disable()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
            .exceptionHandling().authenticationEntryPoint(entryPoint).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
}

@Bean
    public WebSecurityCustomizer customizer(){
    return (web -> web.ignoring().antMatchers("/api/images/**","/api/auth/login"));
}
@Bean
    public WebMvcConfigurer configurer(){
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedMethods("*");
        }
    };
}
}
