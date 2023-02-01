package com.compnay.stp.security.oauth2;

import com.compnay.stp.member.Provider;
import com.compnay.stp.member.Role;
import com.compnay.stp.member.entity.Member;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public class CustomOAuth2User implements OAuth2User {

    private Long userId;
    private Provider userProvider;
    private String userProviderId;
    private String userName;
    private String userEmail;
    private Role role;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public CustomOAuth2User(Long userId, Provider userProvider, String userProviderId, String userName, String userEmail, Role role, Collection<? extends GrantedAuthority> authorities,Map<String, Object> attributes) {
        System.out.println("----------CustomOAuth2User CustomOAuth2User start----------");
        this.userId = userId;
        this.userProvider = userProvider;
        this.userProviderId = userProviderId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.role = role;
        this.authorities = authorities;
        this.attributes = attributes;
    }

    public static CustomOAuth2User create(Member member, Map<String, Object> attributes) {
        System.out.println("----------CustomOAuth2User create start----------");
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority(String.valueOf(member.getRole())));
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(
                member.getMemberId(),
                member.getProvider(),
                member.getProviderId(),
                member.getName(),
                member.getEmail(),
                member.getRole(),
                authorities,
                attributes);

        return customOAuth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return String.valueOf(userId);
    }
}
