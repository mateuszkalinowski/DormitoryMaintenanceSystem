package pl.dormitorymaintenancesystem.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.session.web.http.HeaderHttpSessionStrategy;
//import org.springframework.session.web.http.HttpSessionStrategy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Configuration
    class JdbcSessionConfig {

        @Bean
        public HttpSessionIdResolver httpSessionStrategy() {
            return HeaderHttpSessionIdResolver.xAuthToken();
        }

    }

    @Bean
    public UserDetailsService customUserDetailsService() {
        return new UserLoginService();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .authorizeRequests()
                .antMatchers("/api/login/**").permitAll()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/api/inhabitant/add").permitAll()
                .antMatchers("/api/utils/**").permitAll()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .requestCache()
                .requestCache(new NullRequestCache());


        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfig = new CorsConfiguration().applyPermitDefaultValues();
        corsConfig.addAllowedMethod(HttpMethod.PUT);
        corsConfig.addAllowedMethod(HttpMethod.DELETE);
        corsConfig.addAllowedMethod(HttpMethod.GET);
        corsConfig.addAllowedMethod(HttpMethod.POST);
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }
}
