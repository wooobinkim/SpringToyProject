package com.compnay.stp.security.filter;

import com.compnay.stp.security.service.CustomUserDetailService;
import com.compnay.stp.security.service.LoginUserDetails;
import com.compnay.stp.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends GenericFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;
//    private final UserDetailsService userDetailsService;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("필터의 doFilter에 들어옴");
        String jwtToken = extractToken((HttpServletRequest) request);
        System.out.println("토큰 -> "+jwtToken);
        if(jwtToken != null && jwtUtil.isValidToken(jwtToken)){
            System.out.println("토큰유효");
            UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtUtil.getUserPk(jwtToken));
            LoginUserDetails loginUserDetails = (LoginUserDetails) userDetails;
//            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtil.getUserPk(jwtToken));

            System.out.println("loginUserDetails.getMember().getSiteId() = " + loginUserDetails.getMember().getSiteId());
            System.out.println("loginUserDetails.getMember().getSitePwd() = " + loginUserDetails.getMember().getSitePwd());
            System.out.println("userDetails.getAuthorities() = " + userDetails.getAuthorities());

            Authentication authentication = new UsernamePasswordAuthenticationToken(loginUserDetails.getMember(), null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("authentication.isAuthenticated() = " + authentication.isAuthenticated());
        }

        System.out.println("필터 끝");
        chain.doFilter(request, response);

    }

    private String extractToken(HttpServletRequest request) {
        System.out.println("필터의 extractToken에 들어옴");
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            System.out.println("배리어토큰 파싱 -> "+bearerToken);
            return bearerToken.substring(7);
        }
        return null;
    }

}
