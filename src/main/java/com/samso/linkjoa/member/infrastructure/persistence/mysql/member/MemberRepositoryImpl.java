package com.samso.linkjoa.member.infrastructure.persistence.mysql.member;

import com.samso.linkjoa.member.domain.entity.Member;
import com.samso.linkjoa.member.application.port.out.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final JpaMemberRepository jpaMemberRepository;
    @Override
    public Member save(Member member) {
        return jpaMemberRepository.save(member);
    }
    @Override
    public Optional<Member> findByMail(String mail) {
        return jpaMemberRepository.findByMail(mail);
    }
    @Override
    public Optional<Member> findById(long id) {
        return jpaMemberRepository.findById(id);
    }
}
