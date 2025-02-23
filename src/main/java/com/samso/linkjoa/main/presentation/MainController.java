package com.samso.linkjoa.main.presentation;

import com.samso.linkjoa.main.presentation.port.in.MainInfoUseCase;
import com.samso.linkjoa.main.presentation.web.ResMain;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainInfoUseCase mainInfoUseCase;

    @GetMapping("/v1/main/total")
    public @ResponseBody ResMain getTotalInfo(){
        return mainInfoUseCase.getTotalInfo();
    }
}
