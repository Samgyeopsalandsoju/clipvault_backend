package com.samso.linkjoa.fork.application.service;

import com.samso.linkjoa.clip.Clip;
import com.samso.linkjoa.clip.ClipRepository;
import com.samso.linkjoa.core.common.ApplicationInternalException;
import com.samso.linkjoa.core.springSecurity.JwtUtil;
import com.samso.linkjoa.domain.member.Member;
import com.samso.linkjoa.fork.application.port.out.repository.ForkRepository;
import com.samso.linkjoa.fork.domain.ForkEnum;
import com.samso.linkjoa.fork.domain.entity.Fork;
import com.samso.linkjoa.fork.presentation.port.in.CreateNewForkUseCase;
import com.samso.linkjoa.fork.presentation.port.in.GetForkInfoUseCase;
import com.samso.linkjoa.fork.presentation.web.request.ReqNewFork;
import com.samso.linkjoa.fork.presentation.web.response.ResFork;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForkService implements CreateNewForkUseCase, GetForkInfoUseCase {

    private final ClipRepository clipRepository;
    private final ForkRepository forkRepository;
    @PersistenceContext
    private final EntityManager entityManager;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    @Transactional
    @Override
    public String createFork(HttpServletRequest request, ReqNewFork reqNewFork) {

        Clip clip = clipRepository.findById(reqNewFork.getClipId())
                .filter(c -> "public".equals(c.getVisible()))
                .orElseThrow(() ->new ApplicationInternalException(ForkEnum.NOT_FOUND_CLIP.getValue(), "Not Found Forked Clip"));

        Member loginMember = entityManager.getReference(Member.class, jwtUtil.getMemberIdFromRequest(request));

        forkRepository.save(reqNewFork.toEntity(clip, loginMember));

        long forkCount = clip.getForked_count();
        clip.setForked_count(++forkCount);

         return ForkEnum.CREATE_SUCCESS.getValue();
    }

    @Override
    public List<ResFork> getForkList(HttpServletRequest request) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);

        List<Fork> forkList = forkRepository.findByMemberId(memberId
                                , Sort.by(Sort.Direction.DESC, "createdDate"))
                .orElseThrow(() -> new ApplicationInternalException(ForkEnum.FAIL_FOUND_FORK_LIST.getValue(), "Find Fork List Fail"));

        return forkList.stream()
                .map(fork -> modelMapper.map(fork, ResFork.class))
                .collect(Collectors.toList());
    }
}
