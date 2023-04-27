package com.hana.smallsecurity.application.service;

import com.hana.smallsecurity.application.domain.User;
import com.hana.smallsecurity.application.repository.UserRepository;
import com.hana.smallsecurity.global.secuirty.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

/**
 * 자격증명을 저장해서 스프링 시큐리티가 요청을 인증할 때 사용
 */
@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 예외 인스턴스를 만들기 위한 Supplier 선언
        // 예제에는 있는데 굳이 안해도 될 것 같음
//        Supplier<UsernameNotFoundException> supplier =
//                () -> new UsernameNotFoundException("Problem during authentication");

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Problem during authentication"));

        return new CustomUserDetails(user);
    }
}
