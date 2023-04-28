package com.hana.smallsecurity.global.secuirty;

import com.hana.smallsecurity.application.service.JpaUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 인증 논리를 처리
 */
// 예제에서는 Service annotation을 적욯하였으나 Component로도 충분해보인다. 안되면 뭐... 그때가서 고치지...
//@Service

@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final JpaUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SCryptPasswordEncoder sCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        CustomUserDetails user = userDetailsService.loadUserByUsername(username);

//        switch (user.getUser().getAlgorithm()) {
//        switch (CustomUserDetails.getUserAlgorithm(user)) {
        // CustomUserDetails 안에 넣었는데 망한거같음
        // 실제로 CustomUserDetails안에 해당 메소드를 구현하는건 너무 책임이 방대해진다고 함.
        // 그래서 메소드를 하나 만들어봄
        // 근데 이안에서 결국 get.get 일단 넘어가고 다시 생각
        switch (EncryptionUtils.getUserAlgorithm(user)){
            case BCRYPT -> {
                return checkPassword(user, password, bCryptPasswordEncoder);
            }
            case SCRYPT -> {
                return checkPassword(user, password, sCryptPasswordEncoder);
            }
        }

        throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Authentication checkPassword(CustomUserDetails user, String rawPassword, PasswordEncoder encoder) {
        if(encoder.matches(rawPassword, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        }
        // 예제에서는 if - else 인데, if들어가면 바로 return 해주니까
        // 반대의 경우는 else 없이 그냥 바로 exception
        throw new BadCredentialsException("Bad credentials");
    }
}
