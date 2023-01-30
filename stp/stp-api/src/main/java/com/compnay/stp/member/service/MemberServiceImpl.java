package com.compnay.stp.member.service;

import com.compnay.stp.member.entity.Member;
import com.compnay.stp.member.repository.MemberRepository;
import com.compnay.stp.member.request.MemberCreateRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public void signIn(MemberCreateRequest memberCreateRequest) {
        Member member = Member.create(memberCreateRequest.getSiteId(), memberCreateRequest.getSitePwd(), memberCreateRequest.getName(), memberCreateRequest.getAge());
        memberRepository.save(member);
    }
}
