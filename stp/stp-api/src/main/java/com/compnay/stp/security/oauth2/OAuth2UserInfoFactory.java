package com.compnay.stp.security.oauth2;

import com.compnay.stp.config.ExceptionResponse;
import com.compnay.stp.member.Provider;
import com.compnay.stp.security.handler.OAuthAuthenticationFailureHandler;
import com.compnay.stp.security.handler.OAuthProviderNotExistException;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes){
        System.out.println("----------OAuth2UserInfoFactory getOAuth2UserInfo start----------");
        if(registrationId.equals(Provider.google.toString())){
            return new GoogleOAuth2UserInfo(attributes);
        }else{
            throw new OAuthProviderNotExistException(registrationId);
        }
    }
}
