package com.samso.linkjoa.share;

import com.samso.linkjoa.core.Utility.DateTimeUtil;
import com.samso.linkjoa.core.common.ApplicationInternalException;
import com.samso.linkjoa.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShareService {

    private ShareRepository shareRepository;
    private ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    public void createLink(ShareRequest shareRequest, long memberId) {

        shareRepository.save(Share
                .builder()
                .title(shareRequest.getTitle())
                .link(shareRequest.getLink())
                .due(DateTimeUtil.parseStringToLocalDateTime(
                        shareRequest.getDue(), "yyyy-MM-dd HH:mm:ss"))
                .member(entityManager.getReference(Member.class, memberId))
                .build()
        );
    }

    public List<ShareResponse> getLinkList(long memberId) {

        List<Share> shareList = shareRepository.findByMemberId(memberId, Sort.by(Sort.Direction.DESC, "createdDate"));

        return  shareList.stream()
                .map(share -> modelMapper.map(share, ShareResponse.class))
                .collect(Collectors.toList());
    }

    public String deleteLinkById(Long linkId, long memberId) {

        int deleteCount = shareRepository.deleteByIdAndMemberId(linkId, memberId);

        Optional.of(deleteCount)
                .filter(count -> count >0)
                .orElseThrow(() -> new ApplicationInternalException(ShareEnum.NOT_FOUND_DELETE_SHARE_LINK.getValue(), "Not found delete link"));

        return ShareEnum.DELETE_SHARE_LINK_SUCCESS.getValue();
    }
}
