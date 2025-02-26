package com.samso.linkjoa.clip.presentation.port.in;

import com.samso.linkjoa.clip.presentation.web.response.ResClip;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface GetClipInfoUseCase {


    List<ResClip> findRandomPublicClips(String visible);

    List<ResClip> findRandomPublicClips(int size, String visible);

    List<ResClip> getClipList(HttpServletRequest request);

    ResClip getClipById(HttpServletRequest request, Long clipId);

}
