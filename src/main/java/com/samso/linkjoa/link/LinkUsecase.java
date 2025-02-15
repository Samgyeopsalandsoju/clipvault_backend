package com.samso.linkjoa.link;

import com.samso.linkjoa.core.Utility.DateTimeUtil;
import com.samso.linkjoa.core.common.ApplicationInternalException;
import com.samso.linkjoa.core.springSecurity.JwtUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LinkUsecase {

    private LinkService linkService;
    private JwtUtil jwtUtil;
    @Transactional
    public String createLink(HttpServletRequest request, LinkRequest linkRequest) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);

        linkService.createLink(linkRequest, memberId);

        return LinkEnum.CREATE_LINK_SUCCESS.getValue();
    }

    public List<LinkResponse> getLinkList(HttpServletRequest request) {
        long memberId = jwtUtil.getMemberIdFromRequest(request);

        return linkService.getLinkList(memberId);
    }

    public String deleteLinkById(HttpServletRequest request, Long linkId) {
        long memberId = jwtUtil.getMemberIdFromRequest(request);

        return linkService.deleteLinkById(linkId, memberId);
    }
}
