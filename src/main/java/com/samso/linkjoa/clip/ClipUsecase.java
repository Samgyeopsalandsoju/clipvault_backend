package com.samso.linkjoa.clip;

import com.samso.linkjoa.core.springSecurity.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<ClipResponse> getClipList(HttpServletRequest request) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);

        return clipService.getClipListResponse(memberId);
    }

    public List<CategoryResponse> getCategory(HttpServletRequest request) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);

        return clipService.getCategoryResponse(memberId);
    }

    public String modifyClip(ClipRequest clipRequest) {

        return clipService.modifyClip(clipRequest);
    }

    public ClipResponse getClipById(HttpServletRequest request, Long clipId) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);
        return clipService.getClipById(clipId, memberId);
    }
}
