package com.samso.linkjoa.link;

import com.samso.linkjoa.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LinkRepository  extends JpaRepository<Link, Long> {

    public Link save(Link link);

    List<Link> findByMemberId(long memberId);

    @Transactional
    @Modifying
    int deleteByIdAndMemberId(Long linkId, long memberId);
}
