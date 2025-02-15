package com.samso.linkjoa.link;

import com.samso.linkjoa.core.Utility.DateTimeUtil;
import com.samso.linkjoa.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LinkService {

    private LinkRepository linkRepository;
    private ModelMapper modelMapper;
    public void createLink(LinkRequest linkRequest) {

        linkRepository.save(Link
                .builder()
                .title(linkRequest.getTitle())
                .link(linkRequest.getLink())
                .due(DateTimeUtil.parseStringToLocalDateTime(
                        linkRequest.getDue(), "yyyy-MM-dd HH:mm:ss"))
                .build()
        );
    }

    public List<LinkResponse> getLinkList(long memberId) {

        List<Link> linkList = linkRepository.findByMemberId(memberId);

        return  linkList.stream()
                .map(link -> modelMapper.map(link, LinkResponse.class))
                .collect(Collectors.toList());
    }
}
