package com.samso.linkjoa.share.application.port.out;

import com.samso.linkjoa.share.domain.entity.Share;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

public interface ShareRepository {

    Share save(Share share);
    List<Share> findByDueAfterAndMemberId(LocalDateTime due, long memberId, Sort sort);
    int deleteByIdAndMemberId(Long linkId, long memberId);
    int deleteExpiredShares();
    long countByMemberId(long memberId);
}
