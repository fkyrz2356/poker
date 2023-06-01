package jp.kobe_u.cs27.Poker.application.config;

import jp.kobe_u.cs27.Poker.application.Service.UserService;
//import jp.kobe_u.cs27.Poker.application.config.PasswordEncoderConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
                .loginPage("/login.html")
                .defaultSuccessUrl("/twoPlayers.html", true)
                .failureUrl("/login.html?error")
                .usernameParameter("id")
                .passwordParameter("password")
                .permitAll()
            ).logout(logout -> logout
                .logoutSuccessUrl("/login.html")
                .permitAll()
            ).authorizeHttpRequests(ahr -> ahr
                .requestMatchers("/login.html", "/signup.html").permitAll()
                .anyRequest().authenticated()
            ).csrf((csrf) -> csrf
                .ignoringRequestMatchers("/*")
            );
        return http.build();
    }
}
