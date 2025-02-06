package com.samso.linkjoa.presentation.member.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {
    private String mail;
    private String password;
}
