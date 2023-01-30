package com.compnay.stp.member.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateRequest {
    private String siteId;
    private String sitePwd;
    private String name;
    private String age;
}
