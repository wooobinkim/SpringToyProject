package com.compnay.stp.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/test")
@RestController
@RequiredArgsConstructor
public class testController {

    @GetMapping("")
    public String Test(){
        return "testzzzz";
    }
}
