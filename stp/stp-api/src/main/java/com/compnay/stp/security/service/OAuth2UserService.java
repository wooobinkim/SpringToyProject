package com.compnay.stp.security.service;

import com.compnay.stp.member.Provider;
import com.compnay.stp.member.entity.Member;
import com.compnay.stp.member.repository.MemberRepository;
import com.compnay.stp.security.oauth2.CustomOAuth2User;
import com.compnay.stp.security.oauth2.OAuth2UserInfo;
import com.compnay.stp.security.oauth2.OAuth2UserInfoFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("----------OAuth2UserService loadUser start----------");
        // OAuth로 넘어온 사용자 profile scope가 전처리되는 메서드
        OAuth2User oAuth2User = super.loadUser(userRequest);
        try {
            return proccessOAuth2User(userRequest, oAuth2User);
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            // 여기서 예외가 던져지면 OAuth2AuthenticationFailureHandler에서 처리하게 됨
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }


    private OAuth2User proccessOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User){
        System.out.println("----------OAuth2UserService proccessOAuth2User start----------");
        //신규회원인지 아닌지
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        Optional<Member> optionalMember = memberRepository.findByName(oAuth2UserInfo.getUserName());
        Member member;
        if(optionalMember.isPresent()){
            member = optionalMember.get();
            member.updateGoogle(oAuth2UserInfo.getUserName(), oAuth2UserInfo.getUserProviderId(), oAuth2UserInfo.getUserProvider()
            ,oAuth2UserInfo.getUserEmail(),oAuth2UserInfo.getUserNickname());
        }else{
            member = RegistNewMember(oAuth2UserRequest, oAuth2UserInfo);
        }

        return CustomOAuth2User.create(member, oAuth2User.getAttributes());
    }

    private Member RegistNewMember(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo){
        System.out.println("----------OAuth2UserService RegistNewMember start----------");
        Provider provider = Provider.valueOf(oAuth2UserRequest.getClientRegistration().getClientId());
        String name = oAuth2UserInfo.getUserName();
        String providerId = oAuth2UserInfo.getUserProviderId();
        String nickname = oAuth2UserInfo.getUserNickname();
        String email = oAuth2UserInfo.getUserEmail();

        Member member = Member.createGoogle(name, providerId, provider, email, nickname);

        return memberRepository.save(member);
    }
}
