//package by.academy.springboot.config;
//
//import by.academy.springboot.model.repository.PersonRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//    private final PersonRepository personRepository;
//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/"), AntPathRequestMatcher.antMatcher("/home")).permitAll()
////                        .requestMatchers(
////                                "/ibank",
////                                "/lc",
////                                "/account",
////                                "/ibankCloseAccount")
////                        .hasAnyRole("USER", "ADMIN")
////                        .requestMatchers(
////                                "/customer",
////                                "/customers",
////                                "/closeAccount",
////                                "/terminate",
////                                "/bankAccount",
////                                "/operationsLog",
////                                "/po",
////                                "/persons",
////                                "/newPersonCustomer",
////                                "/newcustomer",
////                                "/createNewCustomer",
////                                "/newAddress",
////                                "/createNewAddress",
////                                "/newbankaccount",
////                                "/createBankAccount",
////                                "/addPhoneNumberCustomer",
////                                "/savePhoneNumberCustomer",
////                                "/addEmailCustomer",
////                                "/saveEmailCustomer")
////                        .hasAnyRole("MANAGER", "ADMIN")
//                )
////                .formLogin(form -> form
////                        .loginPage("/login")
////                        .permitAll()
////                        .defaultSuccessUrl("/home")
////                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login")
//                        .deleteCookies("JSESSIONID")
//                );
//        return http.build();
//    }
//
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }
//}
