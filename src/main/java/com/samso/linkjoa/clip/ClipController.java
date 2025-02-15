package com.samso.linkjoa.clip;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class ClipController {

    private ClipUsecase clipUseCase;

    @PostMapping("/v1/clip/create")
    public @ResponseBody String clipCreate(HttpServletRequest request, @RequestBody ClipRequest clipRequest){

        return clipUseCase.createClip(request, clipRequest);
    }

    @GetMapping("/v1/clip/list")
    public @ResponseBody List<ClipResponse> getClipList(HttpServletRequest request){

        return clipUseCase.getClipList(request);
    }

    @GetMapping("/v1/clip/{clipId}")
    public ClipResponse getClipById(HttpServletRequest request, @PathVariable Long clipId){

        return clipUseCase.getClipById(request, clipId);
    }

    @GetMapping("/v1/category/list")
    public @ResponseBody List<CategoryResponse> getCategoryList(HttpServletRequest request){

        return clipUseCase.getCategory(request);
    }

    @PostMapping("/v1/clip/modify")
    public @ResponseBody String clipModify(HttpServletRequest request, @RequestBody ClipRequest clipRequest){

        return clipUseCase.modifyClip(clipRequest);
    }
}
