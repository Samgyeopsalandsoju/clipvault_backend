package com.samso.linkjoa.clip.presentation.port.in;

import com.samso.linkjoa.clip.presentation.web.response.ClipResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface GetClipInfoUseCase {


    List<ClipResponse> findRandomPublicClips(String visible);

    List<ClipResponse> findRandomPublicClips(int size, String visible);

    List<ClipResponse> getClipList(HttpServletRequest request);

    ClipResponse getClipById(HttpServletRequest request, Long clipId);

}
