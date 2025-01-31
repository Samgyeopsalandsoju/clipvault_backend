package com.samso.linkjoa.presentation.Authentication.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthenticationRequest {
    private String mail;
    private String authCode;
}
