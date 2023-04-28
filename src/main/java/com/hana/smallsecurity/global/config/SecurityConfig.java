package com.hana.smallsecurity.global.config;

import com.hana.smallsecurity.global.secuirty.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 이 클래스는 각종 Security config에 관한 부분을 관리한다.
 */
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final CustomAuthenticationProvider customAuthenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(form -> form.defaultSuccessUrl("/main", true))
                .authorizeRequests(auth -> auth.anyRequest().authenticated())
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return customAuthenticationProvider;
    }

    //TODO: 포스팅 해야해서 남겨놓음
//    @Bean
//    public void authenticationProvider(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(customAuthenticationProvider);
//    }
}
