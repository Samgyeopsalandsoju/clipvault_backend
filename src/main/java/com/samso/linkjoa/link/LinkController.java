package com.samso.linkjoa.link;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class LinkController {

    private LinkUsecase linkUsecase;

    @PostMapping("/v1/link/create")
    public @ResponseBody String createLink(HttpServletRequest request, @RequestBody LinkRequest linkRequest){

        return linkUsecase.createLink(request, linkRequest);
    }

    @GetMapping("/v1/link")
    public @ResponseBody List<LinkResponse> getLink(HttpServletRequest request){

        return linkUsecase.getLinkList(request);
    }

    @DeleteMapping("/v1/link/delete/{linkId}")
    public String deleteLinkById(HttpServletRequest request, @PathVariable Long linkId){

        return linkUsecase.deleteLinkById(request, linkId);
    }
}
