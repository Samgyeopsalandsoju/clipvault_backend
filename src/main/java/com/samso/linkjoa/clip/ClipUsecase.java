package com.samso.linkjoa.clip;

import com.samso.linkjoa.core.springSecurity.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ClipUsecase {

    private JwtUtil jwtUtil;
    private ClipService clipService;

    public String createClip(HttpServletRequest request, ClipRequest clipRequest) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);

        clipService.createClip(clipRequest, memberId);

        return ClipEnum.CREATE_CLIP_SUCCESS.getValue();
    }
}
