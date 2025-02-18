package com.samso.linkjoa.fork.presentation.web;

import com.samso.linkjoa.fork.domain.entity.Fork;
import com.samso.linkjoa.fork.presentation.port.in.CreateNewForkUseCase;
import com.samso.linkjoa.fork.presentation.port.in.GetForkInfoUseCase;
import com.samso.linkjoa.fork.presentation.web.request.ReqNewFork;
import com.samso.linkjoa.fork.presentation.web.response.ResFork;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ForkController {

    private final CreateNewForkUseCase createNewForkUseCase;
    private final GetForkInfoUseCase getForkInfoUseCase;

    @PostMapping("/v1/fork/create")
    public String createNewFork(HttpServletRequest request, @RequestBody ReqNewFork reqNewFork){

        return createNewForkUseCase.createFork(request,reqNewFork);
    };

    @GetMapping("/v1/fork/list")
    public @ResponseBody List<ResFork> getForkList(HttpServletRequest request){

        return getForkInfoUseCase.getForkList(request);
    }

}
