package com.compnay.stp.member.service;

import com.compnay.stp.member.request.MemberCreateRequest;

public interface MemberService {
    public void signIn(MemberCreateRequest memberCreateRequest);
}
