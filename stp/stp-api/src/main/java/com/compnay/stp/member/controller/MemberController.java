package com.compnay.stp.member.controller;

import com.compnay.stp.member.request.MemberCreateRequest;
import com.compnay.stp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/member")
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody MemberCreateRequest memberCreateRequest){
        memberService.signIn(memberCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
