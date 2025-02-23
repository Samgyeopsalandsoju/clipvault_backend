package com.samso.linkjoa.share.infrastructure.persistance.mysql.share;

import com.samso.linkjoa.share.domain.entity.Share;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaShareRepository extends JpaRepository<Share, Long> {
    List<Share> findByMemberId(long memberId, Sort sort);

    int deleteByIdAndMemberId(Long linkId, long memberId);
}
