package com.hana.smallsecurity.global.secuirty;

import com.hana.smallsecurity.application.domain.constant.EncryptionAlgorithm;
import lombok.RequiredArgsConstructor;

/**
 * CustomAuthenticationProvider에서 사용자 암호화 알고리즘을 구현할 때
 * user.getUser().getAlgorithm() 과 같이 get메소드가 두번 호출되는게 너무 맘에 안들어서 만든 유틸리티 클래스
 */
@RequiredArgsConstructor
public class EncryptionUtils {

    private final CustomUserDetails userDetails;

    public static final EncryptionAlgorithm getUserAlgorithm(CustomUserDetails userDetails) {
        return userDetails.getUser().getAlgorithm();
    }
}
