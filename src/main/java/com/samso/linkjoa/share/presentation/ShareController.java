package com.samso.linkjoa.share.presentation;

import com.samso.linkjoa.share.presentation.port.CreateShareInfoUseCase;
import com.samso.linkjoa.share.presentation.port.DeleteShareInfoUseCase;
import com.samso.linkjoa.share.presentation.port.GetShareInfoUseCase;
import com.samso.linkjoa.share.presentation.web.request.ReqShare;
import com.samso.linkjoa.share.presentation.web.response.ResShare;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class ShareController {

    private final CreateShareInfoUseCase createShareInfoUseCase;
    private final GetShareInfoUseCase getShareInfoUseCase;
    private final DeleteShareInfoUseCase deleteShareInfoUseCase;

    @PostMapping("/v1/share/create")
    public @ResponseBody String createLink(HttpServletRequest request, @RequestBody ReqShare reqShare){

        return createShareInfoUseCase.createLink(request, reqShare);
    }

    @GetMapping("/v1/share/list")
    public @ResponseBody List<ResShare> getLink(HttpServletRequest request){

        return getShareInfoUseCase.getLinkList(request);
    }

    @DeleteMapping("/v1/share/delete/{shareId}")
    public String deleteLinkById(HttpServletRequest request, @PathVariable Long shareId){

        return deleteShareInfoUseCase.deleteLinkById(request, shareId);
    }
}
