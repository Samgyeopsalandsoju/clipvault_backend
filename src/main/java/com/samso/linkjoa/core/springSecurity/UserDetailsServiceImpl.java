package com.samso.linkjoa.core.springSecurity;

import com.samso.linkjoa.core.Utility.Encryptor;
import com.samso.linkjoa.domain.member.Member;
import com.samso.linkjoa.domain.member.MemberEnum;
import com.samso.linkjoa.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@SecondaryRow
@RequiredArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String memberMail) throws UsernameNotFoundException {

        Member member = memberRepository.findByMail(Encryptor.twoWayEncrypt(memberMail))
                .orElseThrow(() -> new UsernameNotFoundException(MemberEnum.NOT_FOUND_USER.getValue()));

        //여러 권한일 경우
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(member.getRole().name())
        );
        return new UserDetailsImpl(
                member.getId(),
                member.getMail(),
                member.getPassword(),
                authorities
        );
    }

    public UserDetails loadUserByMemberId(String memberId) {
        Member member = memberRepository.findById(Long.valueOf(memberId))
                .orElseThrow(() -> new UsernameNotFoundException(MemberEnum.NOT_FOUND_USER.getValue()));

        //여러 권한일 경우
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(member.getRole().name())
        );
        return new UserDetailsImpl(
                member.getId(),
                member.getMail(),
                member.getPassword(),
                authorities
        );
    }
}
