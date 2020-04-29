package com.dededesignworkshop.seisankun_api.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Configuration
public class BasicAuthProvider implements AuthenticationProvider {
    @Value("${seisankun.api.basic.user}")
    private String BASIC_USER;

    @Value("${seisankun.api.basic.password}")
    private String BASIC_PASSWORD;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String inputName = authentication.getName();
        String inputPass = authentication.getCredentials().toString();

        String name = BASIC_USER;
        String pass = BASIC_PASSWORD;

        if (inputName.equals(name) && inputPass.equals(pass)) {
            return new UsernamePasswordAuthenticationToken(inputName, inputPass, authentication.getAuthorities());
        } else {
            throw new BadCredentialsException("ユーザー名やパスワードが正しくありません");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
