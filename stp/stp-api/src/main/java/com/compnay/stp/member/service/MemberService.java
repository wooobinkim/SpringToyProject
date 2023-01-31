package com.compnay.stp.member.service;

import com.compnay.stp.member.request.MemberCreateRequest;
import com.compnay.stp.member.request.MemberLoginRequest;

public interface MemberService {
    public void signIn(MemberCreateRequest memberCreateRequest);
    public String login(MemberLoginRequest memberLoginRequest);
}
