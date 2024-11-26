package gdgserver.practice.simpleboard.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import gdgserver.practice.simpleboard.jwt.CustomLoginAuthenticationFilter;
import gdgserver.practice.simpleboard.jwt.JwtAuthenticationFilter;
import gdgserver.practice.simpleboard.jwt.service.JwtService;
import gdgserver.practice.simpleboard.jwt.LoginFailureHandler;
import gdgserver.practice.simpleboard.jwt.LoginSuccessHandler;
import gdgserver.practice.simpleboard.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity _http) throws Exception {
         return _http.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterAfter(loginFilter(), LogoutFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(), CustomLoginAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/signup/**", "/api/users/login/**")
                        .permitAll()
                        .anyRequest().authenticated())
                .build();
    }

    // password 인코더 등록
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager 등록
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider provider = authProvider();

        return new ProviderManager(provider);
    }

    @Bean
    public DaoAuthenticationProvider authProvider() throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler(){

        return new LoginSuccessHandler(jwtService, refreshTokenRepository);

    }

    @Bean
    public LoginFailureHandler loginFailureHandler(){

        return new LoginFailureHandler();
    }

    @Bean
    public CustomLoginAuthenticationFilter loginFilter()
            throws Exception {
        CustomLoginAuthenticationFilter filter
                = new CustomLoginAuthenticationFilter(objectMapper);

        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(loginSuccessHandler());
        filter.setAuthenticationFailureHandler(loginFailureHandler());

        return filter;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter(jwtService, userDetailsService,refreshTokenRepository);
    }
}
