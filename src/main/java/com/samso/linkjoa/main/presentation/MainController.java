package com.samso.linkjoa.main.presentation;

import com.samso.linkjoa.main.presentation.port.in.MainInfoUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainInfoUseCase mainInfoUseCase;

    @GetMapping("/v1/main/clip/total")
    public long getClipTotalInfo(){
        return mainInfoUseCase.getClipTotalCount();
    }
    @GetMapping("/v1/main/share/total")
    public @ResponseBody long getShareTotalInfo(){
        return mainInfoUseCase.getShareTotalCount();
    }

    @GetMapping("/v1/main/fork/forkedList")
    public @ResponseBody List<Long> getForkedList(HttpServletRequest request){
        return mainInfoUseCase.getForkedList(request);
    }
}
