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
    public @ResponseBody String categoryCreate(HttpServletRequest request, @RequestBody ClipRequest clipRequest){

        return clipUseCase.createClip(request, clipRequest);
    }

    @GetMapping("/v1/clip/list")
    public @ResponseBody List<ClipResponse> getClipList(HttpServletRequest request){

        return  clipUseCase.getClipList(request);
    }
}
