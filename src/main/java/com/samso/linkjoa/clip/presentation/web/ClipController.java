package com.samso.linkjoa.clip.presentation.web;

import com.samso.linkjoa.clip.presentation.port.in.CreateClipUseCase;
import com.samso.linkjoa.clip.presentation.port.in.DeleteClipUseCase;
import com.samso.linkjoa.clip.presentation.port.in.GetClipInfoUseCase;
import com.samso.linkjoa.clip.presentation.port.in.ModifyClipUseCase;
import com.samso.linkjoa.clip.presentation.web.request.ClipRequest;
import com.samso.linkjoa.clip.presentation.web.response.ClipResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class ClipController {

    private CreateClipUseCase createClip;
    private GetClipInfoUseCase getClipInfoUseCase;
    private ModifyClipUseCase modifyClipUseCase;
    private DeleteClipUseCase deleteClipUseCase;

    @PostMapping("/v1/clip/create")
    public @ResponseBody String clipCreate(HttpServletRequest request, @RequestBody ClipRequest clipRequest){

        return createClip.createClip(request, clipRequest);
    }

    @GetMapping("/v1/clip/list")
    public @ResponseBody List<ClipResponse> getClipList(HttpServletRequest request){

        return getClipInfoUseCase.getClipList(request);
    }

    @GetMapping("/v1/clip/{clipId}")
    public ClipResponse getClipById(HttpServletRequest request, @PathVariable Long clipId){

        return getClipInfoUseCase.getClipById(request, clipId);
    }

    @PatchMapping("/v1/clip/modify")
    public @ResponseBody String clipModify(HttpServletRequest request, @RequestBody ClipRequest clipRequest){

        return modifyClipUseCase.modifyClip(clipRequest);
    }

    @DeleteMapping("/v1/clip/delete/{clipId}")
    public String deleteClipById(HttpServletRequest request, @PathVariable Long clipId){

        return deleteClipUseCase.deleteClipById(request, clipId);
    }
}
