package com.samso.linkjoa.link;

import com.samso.linkjoa.core.Utility.DateTimeUtil;
import com.samso.linkjoa.core.springSecurity.JwtUtil;
import com.samso.linkjoa.domain.member.Member;
import io.jsonwebtoken.Jwt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LinkService {

    private LinkRepository linkRepository;
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    public void createLink(LinkRequest linkRequest, long memberId) {

        linkRepository.save(Link
                .builder()
                .title(linkRequest.getTitle())
                .link(linkRequest.getLink())
                .due(DateTimeUtil.parseStringToLocalDateTime(
                        linkRequest.getDue(), "yyyy-MM-dd HH:mm:ss"))
                .member(entityManager.getReference(Member.class, memberId))
                .build()
        );
    }

    public List<LinkResponse> getLinkList(long memberId) {

        List<Link> linkList = linkRepository.findByMemberId(memberId);

        return  linkList.stream()
                .map(link -> modelMapper.map(link, LinkResponse.class))
                .collect(Collectors.toList());
    }

    public String deleteLinkById(Long linkId, long memberId) {

        int deleteCount = linkRepository.deleteByIdAndMemberId(linkId, memberId);

        return deleteCount > 0 ?
                LinkEnum.DELETE_LINK_SUCCESS.getValue()
                : LinkEnum.NOT_FOUND_DELETE_LINK.getValue();
    }
}
