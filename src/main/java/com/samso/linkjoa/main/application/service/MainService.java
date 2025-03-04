package com.samso.linkjoa.main.application.service;

import com.samso.linkjoa.clip.application.port.out.repository.ClipRepository;
import com.samso.linkjoa.core.springSecurity.JwtUtil;
import com.samso.linkjoa.fork.application.port.out.repository.ForkRepository;
import com.samso.linkjoa.main.application.port.out.TotalShareRepository;
import com.samso.linkjoa.main.presentation.port.in.MainInfoUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MainService implements MainInfoUseCase {

    private JwtUtil jwtUtil;
    private ClipRepository clipRepository;
    private TotalShareRepository totalShareRepository;
    private ForkRepository forkRepository;
    @Override
    public long getClipTotalCount() { return clipRepository.count(); }
    @Override
    public long getShareTotalCount() {
        return totalShareRepository.findById(1L).get().getTotalCount();
    }

    @Override
    public List<Long> getForkedList(HttpServletRequest request) {
        long memberId = jwtUtil.getMemberIdFromRequest(request);
        return forkRepository.findByMemberId(memberId);
    }
}
