package by.academy.springboot.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private static final String LOGIN_PAGE = "/login";
    private static final String LOGOUT_PAGE = "/logout";
    private static final String REGISTRATION_PAGE = "/registration";
    private static final String HOME_PAGE = "/home";
    private static final String REGISTER_PAGE = "/register";


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        http
                .csrf(AbstractHttpConfigurer::disable)
                .requestCache(cache ->cache.requestCache(requestCache))
                .authorizeHttpRequests(authz -> authz
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .requestMatchers(
                                        antMatcher(REGISTRATION_PAGE),
                                        antMatcher(REGISTER_PAGE),
                                        antMatcher(LOGIN_PAGE),
                                        antMatcher("/resources/**"),
                                        antMatcher("/static/**"),
                                        antMatcher("/.*.css"),
                                        antMatcher("/js/**"),
                                        antMatcher("/fonts/**")
                                ).permitAll()
                        .anyRequest().authenticated())

                .formLogin(
                        login -> login
                                .loginPage(LOGIN_PAGE)
                                .defaultSuccessUrl(HOME_PAGE)
                )

                .logout(logout -> logout
                        .logoutUrl(LOGOUT_PAGE)
                        .logoutSuccessUrl(LOGIN_PAGE)
                )
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
