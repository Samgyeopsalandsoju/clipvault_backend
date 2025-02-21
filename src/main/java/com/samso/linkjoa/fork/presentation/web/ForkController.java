package com.samso.linkjoa.fork.presentation.web;

import com.samso.linkjoa.fork.presentation.port.in.CreateNewForkUseCase;
import com.samso.linkjoa.fork.presentation.port.in.DeleteForkUseCase;
import com.samso.linkjoa.fork.presentation.port.in.GetForkInfoUseCase;
import com.samso.linkjoa.fork.presentation.web.request.ReqFork;
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
    private final DeleteForkUseCase deleteForkUseCase;

    @PostMapping("/v1/fork/create")
    public String createNewFork(HttpServletRequest request, @RequestBody ReqFork reqFork){

        return createNewForkUseCase.createFork(request, reqFork);
    };

    @GetMapping("/v1/fork/list")
    public @ResponseBody List<ResFork> getForkList(HttpServletRequest request){

        return getForkInfoUseCase.getForkList(request);
    }

    @DeleteMapping("/v1/fork/delete")
    public String deleteForkClip(HttpServletRequest request, @RequestBody ReqFork reqFork){

        return deleteForkUseCase.deleteForkClip(request, reqFork);
    }
}
