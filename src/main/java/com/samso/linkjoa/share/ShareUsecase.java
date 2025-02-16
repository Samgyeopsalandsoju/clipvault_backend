package com.samso.linkjoa.share;

import com.samso.linkjoa.core.springSecurity.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ShareUsecase {

    private ShareService shareService;
    private JwtUtil jwtUtil;
    @Transactional
    public String createLink(HttpServletRequest request, ShareRequest shareRequest) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);

        shareService.createLink(shareRequest, memberId);

        return ShareEnum.CREATE_SHARE_LINK_SUCCESS.getValue();
    }

    public List<ShareResponse> getLinkList(HttpServletRequest request) {
        long memberId = jwtUtil.getMemberIdFromRequest(request);

        return shareService.getLinkList(memberId);
    }

    public String deleteLinkById(HttpServletRequest request, Long linkId) {
        long memberId = jwtUtil.getMemberIdFromRequest(request);

        return shareService.deleteLinkById(linkId, memberId);
    }
}
