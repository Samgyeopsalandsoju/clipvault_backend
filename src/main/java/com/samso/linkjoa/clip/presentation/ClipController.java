package com.samso.linkjoa.clip.presentation;

import com.samso.linkjoa.clip.presentation.port.in.CreateClipUseCase;
import com.samso.linkjoa.clip.presentation.port.in.DeleteClipUseCase;
import com.samso.linkjoa.clip.presentation.port.in.GetClipInfoUseCase;
import com.samso.linkjoa.clip.presentation.port.in.ModifyClipUseCase;
import com.samso.linkjoa.clip.presentation.web.request.ReqClip;
import com.samso.linkjoa.clip.presentation.web.response.ResClip;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class ClipController {

    private CreateClipUseCase createClipUseCase;
    private GetClipInfoUseCase getClipInfoUseCase;
    private ModifyClipUseCase modifyClipUseCase;
    private DeleteClipUseCase deleteClipUseCase;

    @GetMapping("/v1/clip/public/{pageSize}")
    public @ResponseBody List<ResClip> getPublicClipList(@PathVariable int pageSize){
        return getClipInfoUseCase.findRandomPublicClips(pageSize,"public");
    }
    @GetMapping("/v1/clip/public")
    public @ResponseBody List<ResClip> getPublicClipList(){
        return getClipInfoUseCase.findRandomPublicClips("public");
    }
    @PostMapping("/v1/clip/create")
    public @ResponseBody String clipCreate(HttpServletRequest request, @RequestBody ReqClip reqClip){

        return createClipUseCase.createClip(request, reqClip);
    }

    @GetMapping("/v1/clip/list")
    public @ResponseBody List<ResClip> getClipList(HttpServletRequest request){

        return getClipInfoUseCase.getClipList(request);
    }

    @GetMapping("/v1/clip/{clipId}")
    public ResClip getClipById(HttpServletRequest request, @PathVariable Long clipId){

        return getClipInfoUseCase.getClipById(request, clipId);
    }

    @PatchMapping("/v1/clip/modify")
    public @ResponseBody String clipModify(HttpServletRequest request, @RequestBody ReqClip reqClip){

        return modifyClipUseCase.modifyClip(reqClip);
    }

    @DeleteMapping("/v1/clip/delete/{clipId}")
    public String deleteClipById(HttpServletRequest request, @PathVariable Long clipId){

        return deleteClipUseCase.deleteClipById(request, clipId);
    }
}
