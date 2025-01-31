package com.samso.linkjoa.infrastructure.redis;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RedisOffSetEnum {

    AuthSignUp(180);

    private final long value;
}
