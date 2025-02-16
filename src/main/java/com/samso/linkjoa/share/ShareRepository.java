package com.samso.linkjoa.share;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {

    public Share save(Share share);

    List<Share> findByMemberId(long memberId, Sort sort);

    @Transactional
    @Modifying
    int deleteByIdAndMemberId(Long linkId, long memberId);
}
