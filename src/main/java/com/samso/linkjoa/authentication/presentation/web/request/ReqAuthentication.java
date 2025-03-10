package com.samso.linkjoa.authentication.presentation.web.request;

import lombok.Value;

@Value
public class ReqAuthentication {
    String authKey;
    String mail;
    String authCode;
}
