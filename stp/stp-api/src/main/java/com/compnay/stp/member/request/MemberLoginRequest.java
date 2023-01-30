package com.compnay.stp.member.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginRequest {
    private String siteId;
    private String sitePwd;
}
