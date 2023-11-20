//package by.academy.springboot.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.firewall.*;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig
////extends WebSecurityConfigurerAdapter
//{
//    private final UserDetailsService userDetailsService;
//
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.authenticationProvider(authenticationProvider());
////    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/login")
//                                )
//                                .permitAll()
//                                .anyRequest()
//                                .authenticated())
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/")
//                        .permitAll()
//                        )
//                .logout(LogoutConfigurer::permitAll)
//
//
//
//
////                        .requestMatchers(AntPathRequestMatcher.antMatcher("/login"))
////                        .permitAll()
////
////                        .requestMatchers(AntPathRequestMatcher.antMatcher("/ibank"))
////                        .hasAnyRole("USER", "ADMIN")
////                        .requestMatchers(AntPathRequestMatcher.antMatcher("/customer"))
////                        .hasAnyRole("MANAGER", "ADMIN")
////                        .anyRequest()
////                        .authenticated()
////                )
////                .formLogin(form -> form
////                        .loginPage("/login")
////                        .loginProcessingUrl("/")
////                        .permitAll()
//////                        .defaultSuccessUrl("/home")
////                )
////                .logout(logout -> logout
////                        .logoutUrl("/logout")
////                        .logoutSuccessUrl("/")
////                )
//                .build();
////        return http.build();
//    }
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//
//
//}
