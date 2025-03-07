package com.samso.linkjoa.share.infrastructure.persistance.mysql.share;

import com.samso.linkjoa.share.domain.entity.Share;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaShareRepository extends JpaRepository<Share, Long> {
    List<Share> findByDueAfterAndMemberId(LocalDateTime due, long memberId, Sort sort);

    @Modifying
    int deleteByIdAndMemberId(Long linkId, long memberId);

    @Modifying
    @Query("delete from Share s WHERE s.due < current timestamp")
    int deleteExpiredShares();
}
