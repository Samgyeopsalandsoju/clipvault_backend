package com.samso.linkjoa.share.application.port.out;

import com.samso.linkjoa.share.domain.entity.Share;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShareRepository {

    Share save(Share share);

    List<Share> findByMemberId(long memberId, Sort sort);

    @Transactional
    @Modifying
    int deleteByIdAndMemberId(Long linkId, long memberId);
}
