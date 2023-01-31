package com.compnay.stp.member.service;

import com.compnay.stp.member.entity.Member;
import com.compnay.stp.member.repository.MemberRepository;
import com.compnay.stp.member.request.MemberCreateRequest;
import com.compnay.stp.member.request.MemberLoginRequest;
import com.compnay.stp.security.util.JwtUtil;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    @Override
    @Transactional
    public void signIn(MemberCreateRequest memberCreateRequest) {
        Member member = Member.create(memberCreateRequest.getSiteId(), memberCreateRequest.getSitePwd(), memberCreateRequest.getName(), memberCreateRequest.getAge());
        memberRepository.save(member);
    }

    @Override
    public String login(MemberLoginRequest memberLoginRequest) {
        System.out.println("로그인 서비스 들어옴");
        Member member = memberRepository.findBySiteId(memberLoginRequest.getSiteId());

        if(!member.getSitePwd().equals(memberLoginRequest.getSitePwd())) System.out.println("비밀번호틀림");
        else {
            String accessToken = jwtUtil.createToken(member.getMemberId(), member.getRole());
            System.out.println("토큰생성완료 -> " + accessToken);

            return accessToken;
        }

        return null;
    }
}
