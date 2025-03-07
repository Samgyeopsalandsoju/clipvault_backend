package com.samso.linkjoa.share.infrastructure.persistance.mysql.share;

import com.samso.linkjoa.share.domain.entity.Share;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaShareRepository extends JpaRepository<Share, Long> {
    List<Share> findByDueAfterAndMemberId(LocalDateTime due, long memberId, Sort sort);

    int deleteByIdAndMemberId(Long linkId, long memberId);
}
