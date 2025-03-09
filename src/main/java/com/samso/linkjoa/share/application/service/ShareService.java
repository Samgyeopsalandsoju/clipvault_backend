package com.samso.linkjoa.share.application.service;

import com.samso.linkjoa.core.common.commonEnum.CreateLimitEnum;
import com.samso.linkjoa.core.utility.DateTimeUtil;
import com.samso.linkjoa.core.common.exception.ApplicationInternalException;
import com.samso.linkjoa.core.springSecurity.JwtUtil;
import com.samso.linkjoa.domain.member.Member;
import com.samso.linkjoa.main.application.port.out.TotalShareRepository;
import com.samso.linkjoa.main.domain.entity.TotalShare;
import com.samso.linkjoa.share.application.port.out.ShareRepository;
import com.samso.linkjoa.share.domain.ShareEnum;
import com.samso.linkjoa.share.domain.entity.Share;
import com.samso.linkjoa.share.presentation.port.CreateShareInfoUseCase;
import com.samso.linkjoa.share.presentation.port.DeleteShareInfoUseCase;
import com.samso.linkjoa.share.presentation.port.GetShareInfoUseCase;
import com.samso.linkjoa.share.presentation.web.request.ReqShare;
import com.samso.linkjoa.share.presentation.web.response.ResShare;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShareService implements CreateShareInfoUseCase, GetShareInfoUseCase, DeleteShareInfoUseCase{

    private final JwtUtil jwtUtil;
    private final ShareRepository shareRepository;
    private final TotalShareRepository totalShareRepository;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public String createLink(HttpServletRequest request, ReqShare reqShare) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);

        //만들 수 있는 최대 수 체크
        long myShareCount = shareRepository.countByMemberId(memberId);
        if(myShareCount >= CreateLimitEnum.SHARE.getValue()){
            throw new ApplicationInternalException(String.valueOf(CreateLimitEnum.OVER.getValue()), "Over the maximum limit of share");
        }

        shareRepository.save(Share
                .builder()
                .title(reqShare.getTitle())
                .link(reqShare.getLink())
                .due(DateTimeUtil.parseStringToLocalDateTime(
                        reqShare.getDue(), "yyyy-MM-dd HH:mm:ss"))
                .member(entityManager.getReference(Member.class, memberId))
                .build()
        );

        TotalShare totalShare = totalShareRepository.findById(1L).get();
        totalShare.addTotalShareCount(totalShare);
        return ShareEnum.CREATE_SHARE_LINK_SUCCESS.getValue();
    }

    @Override
    public List<ResShare> getLinkList(HttpServletRequest request) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);

        List<Share> shareList = shareRepository.findByDueAfterAndMemberId(LocalDateTime.now(),memberId, Sort.by(Sort.Direction.DESC, "createdDate"));

        return  shareList.stream()
                .map(share -> modelMapper.map(share, ResShare.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public String deleteLinkById(HttpServletRequest request, Long linkId) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);

        int deleteCount = shareRepository.deleteByIdAndMemberId(linkId, memberId);

        Optional.of(deleteCount)
                .filter(count -> count > 0)
                .orElseThrow(() -> new ApplicationInternalException(ShareEnum.NOT_FOUND_DELETE_SHARE_LINK.getValue(), "Not found delete link"));

        return ShareEnum.DELETE_SHARE_LINK_SUCCESS.getValue();
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void deleteExpireDueBatch() {
        int deletedCount = shareRepository.deleteExpiredShares();
    }
}
