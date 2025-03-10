package com.samso.linkjoa.member.infrastructure.persistence.mysql.member;

import com.samso.linkjoa.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaMemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMail(String mail);
}
