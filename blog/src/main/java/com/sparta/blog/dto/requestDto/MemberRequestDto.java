package com.sparta.blog.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
public class MemberRequestDto {

    @Pattern(regexp="^[a-zA-Z0-9]{4,12}$", message = "닉네임은 최소 4자 이상, 12자 이하 알파벳 대소문자(a-z, A-Z), 숫자(0-9)로 구성됩니다.")
    private String nickname;

    @Pattern(regexp="^[a-z0-9]{4,32}$", message = "비밀번호는 최소 4자 이상, 32자 이하 알파벳 소문자(a-z), 숫자(0-9)로 구성됩니다.")
    private String password;

    private String passwordConfirm;

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(nickname, password);
    }
}
