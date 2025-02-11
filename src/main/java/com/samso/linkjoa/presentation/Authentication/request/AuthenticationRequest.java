package com.samso.linkjoa.presentation.Authentication.request;

import lombok.Value;

@Value
public class AuthenticationRequest {
    String authKey;
    String mail;
    String authCode;
}
