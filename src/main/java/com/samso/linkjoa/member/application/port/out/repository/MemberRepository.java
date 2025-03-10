package com.samso.linkjoa.member.application.port.out.repository;

import com.samso.linkjoa.member.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository {

    public Member save(Member member);
    public Optional<Member> findByMail(String mail);
    public Optional<Member> findById(long id);
}
