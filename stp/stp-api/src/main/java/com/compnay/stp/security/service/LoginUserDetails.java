package com.compnay.stp.security.service;

import com.compnay.stp.member.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Setter
@Getter
public class LoginUserDetails extends User {
    private Member member;

    public LoginUserDetails(String memberId, String password, Collection<? extends GrantedAuthority> authorities){
        super(memberId, password, authorities);
    }
}
