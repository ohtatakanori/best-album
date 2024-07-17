package com.example.bestalbam.dev;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.example.bestalbam.model.Authority;
import com.example.bestalbam.model.CustomUserDetails;
import com.example.bestalbam.model.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Component
@Profile("dev")
public class DevAuthenticationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // ダミーの権限を生成
        Authority dummyAuthority = new Authority();
        dummyAuthority.setId(1L); // 仮のID
        dummyAuthority.setAuthority("ROLE_CONTRIBUTOR"); // 仮のROLE

        // ダミーのユーザーを生成
        User dummyUser = new User();
        dummyUser.setId(1L); // 仮のID
        dummyUser.setUsername("dummyViewer"); // 仮のusername
        dummyUser.setPassword("dummyPassword");
        dummyUser.setEnabled(true);
        dummyUser.setAuthorities(
                new HashSet<>(Collections.singletonList(dummyAuthority)));

        CustomUserDetails user = new CustomUserDetails(dummyUser);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        // SecurityContext に設定
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
        }
}

