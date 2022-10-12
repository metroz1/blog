package com.sparta.blog.controller;

import com.sparta.blog.dto.TokenDto;
import com.sparta.blog.dto.requestDto.MemberRequestDto;
import com.sparta.blog.dto.requestDto.TokenRequestDto;
import com.sparta.blog.dto.responseDto.MemberResponseDto;
import com.sparta.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody @Valid MemberRequestDto memberRequestDto) {

        return memberService.signup(memberRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberResponseDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.login(memberRequestDto);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        TokenDto tokenDto = new TokenDto();
        return new ResponseEntity(tokenDto, HttpStatus.OK);
    }
}
