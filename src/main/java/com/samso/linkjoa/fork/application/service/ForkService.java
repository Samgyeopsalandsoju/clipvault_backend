package com.samso.linkjoa.fork.application.service;

import com.samso.linkjoa.clip.application.port.out.repository.ClipRepository;
import com.samso.linkjoa.clip.domain.entity.Clip;
import com.samso.linkjoa.core.common.ApplicationInternalException;
import com.samso.linkjoa.core.springSecurity.JwtUtil;
import com.samso.linkjoa.domain.member.Member;
import com.samso.linkjoa.fork.application.port.out.repository.ForkRepository;
import com.samso.linkjoa.fork.domain.ForkEnum;
import com.samso.linkjoa.fork.domain.entity.Fork;
import com.samso.linkjoa.fork.presentation.port.in.CreateNewForkUseCase;
import com.samso.linkjoa.fork.presentation.port.in.DeleteForkUseCase;
import com.samso.linkjoa.fork.presentation.port.in.GetForkInfoUseCase;
import com.samso.linkjoa.fork.presentation.web.request.ReqFork;
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
public class ForkService implements CreateNewForkUseCase, GetForkInfoUseCase, DeleteForkUseCase {

    private final ClipRepository clipRepository;
    private final ForkRepository forkRepository;
    @PersistenceContext
    private final EntityManager entityManager;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    @Transactional
    @Override
    public String createFork(HttpServletRequest request, ReqFork reqFork) {

        //TODO 1. 클립 정보가 없거나  2. 내클립이거나 3. 이미 포크한 클립이면 튕겨내야함
        Clip clip = clipRepository.findById(reqFork.getClipId())
                .filter(c -> "public".equals(c.getVisible()))
                .orElseThrow(() ->new ApplicationInternalException(ForkEnum.NOT_FOUND_CLIP.getValue(), "Not Found Forked Clip"));

        Member loginMember = entityManager.getReference(Member.class, jwtUtil.getMemberIdFromRequest(request));

        forkRepository.save(reqFork.toEntity(clip, loginMember));

        long forkCount = clip.getForkedCount();
        clip.setForkedCount(++forkCount);

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

    @Override
    @Transactional
    public String deleteForkClip(HttpServletRequest request, ReqFork reqFork) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);

        forkRepository.deleteByIdAndMemberId(reqFork.getForkId(), memberId)
                .orElseThrow(() -> new ApplicationInternalException(ForkEnum.DELETE_FAIL.getValue(), "Delete Forked Clip Fail"));

        clipRepository.findById(reqFork.getClipId())
                        .ifPresent(c -> {
                            long forkedCount = c.getForkedCount();
                            c.setForkedCount(--forkedCount);
                        });

        return ForkEnum.DELETE_SUCCESS.getValue();
    }
}
