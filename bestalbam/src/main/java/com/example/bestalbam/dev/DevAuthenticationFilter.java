package com.example.bestalbam.dev;

import java.io.IOException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

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

        // ダミーのユーザーを生成
        User dummyUser = new User();
        dummyUser.setId(1L); // 仮のID
        dummyUser.setUsername("dummyTeacher"); // 仮のusername
        dummyUser.setPassword("dummyPassword");
        // dummyUser.setEnabled(true);

        // CustomUserDetails user = new CustomUserDetails(dummyUser);
        // UsernamePasswordAuthenticationToken authentication =
        //         new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        // SecurityContext に設定
        // SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }
}

