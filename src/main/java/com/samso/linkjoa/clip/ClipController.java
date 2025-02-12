package com.samso.linkjoa.clip;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ClipController {

    private ClipUsecase clipUseCase;

    @PostMapping("/v1/clip/create")
    public @ResponseBody String categoryCreate(HttpServletRequest request, @RequestBody ClipRequest clipRequest){

        return clipUseCase.createClip(request, clipRequest);
    }
}
