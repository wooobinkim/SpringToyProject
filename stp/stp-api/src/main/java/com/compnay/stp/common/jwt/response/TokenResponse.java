package com.compnay.stp.common.jwt.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponse {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
