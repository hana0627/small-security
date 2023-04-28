package com.hana.smallsecurity.global.secuirty;

import com.hana.smallsecurity.application.domain.User;
import com.hana.smallsecurity.application.domain.constant.EncryptionAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 사용자에 대한 프로토타입
 * 유저에 대한 인증 설계도(?)
 */
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final User user;

    public final User getUser() {
        return user;
    }

    public static final EncryptionAlgorithm getUserAlgorithm(CustomUserDetails customUserDetails) {
        return customUserDetails.getUser().getAlgorithm();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
