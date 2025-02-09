package com.samso.linkjoa.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member save(Member member);
    public Optional<Member> findByMail(String mail);
    public Optional<Member> findById(long id);
}
