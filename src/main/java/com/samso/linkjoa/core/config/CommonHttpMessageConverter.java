package com.samso.linkjoa.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samso.linkjoa.core.common.ApiResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)//TODO 뭐지?
public class CommonHttpMessageConverter extends AbstractHttpMessageConverter<ApiResponse<Object>> {

    private final ObjectMapper objectMapper;

    public CommonHttpMessageConverter(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean supports(Class<?> clazz) {

        //return clazz.equals(ApiResponse.class) || clazz.isPrimitive() || clazz.equals(String.class);
        return clazz.isPrimitive() || clazz.equals(String.class);
    }

    @Override
    protected ApiResponse<Object> readInternal(Class<? extends ApiResponse<Object>> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        return objectMapper.readValue(inputMessage.getBody(), clazz);
    }

    @Override
    protected void writeInternal(ApiResponse<Object> resultMessage, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        String responseMessage = this.objectMapper.writeValueAsString(resultMessage);
    }
}
