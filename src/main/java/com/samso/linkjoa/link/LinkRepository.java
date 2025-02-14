package com.samso.linkjoa.link;

import com.samso.linkjoa.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository  extends JpaRepository<Link, Long> {

    public Link save(Link link);

    List<Link> findByMemberId(long memberId);
}
