package com.samso.linkjoa.share;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class ShareController {

    private ShareUsecase shareUsecase;

    @PostMapping("/v1/share/create")
    public @ResponseBody String createLink(HttpServletRequest request, @RequestBody ShareRequest shareRequest){

        return shareUsecase.createLink(request, shareRequest);
    }

    @GetMapping("/v1/share/list")
    public @ResponseBody List<ShareResponse> getLink(HttpServletRequest request){

        return shareUsecase.getLinkList(request);
    }

    @DeleteMapping("/v1/share/delete/{shareId}")
    public String deleteLinkById(HttpServletRequest request, @PathVariable Long shareId){

        return shareUsecase.deleteLinkById(request, shareId);
    }
}
