package com.compnay.stp.member.controller;

import com.compnay.stp.member.request.MemberCreateRequest;
import com.compnay.stp.member.request.MemberLoginRequest;
import com.compnay.stp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping ("/login")
    public ResponseEntity<?> login(@RequestBody MemberLoginRequest memberLoginRequest){
        System.out.println("로그인 컨트롤러 들어옴");
        String token = memberService.login(memberLoginRequest);

        return ResponseEntity.status(HttpStatus.OK).header("AccessToken",token).build();
    }
}
