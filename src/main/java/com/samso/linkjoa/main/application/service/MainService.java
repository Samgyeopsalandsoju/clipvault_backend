package com.samso.linkjoa.main.application.service;

import com.samso.linkjoa.clip.application.port.out.repository.ClipRepository;
import com.samso.linkjoa.main.application.port.out.TotalShareRepository;
import com.samso.linkjoa.main.domain.entity.TotalShare;
import com.samso.linkjoa.main.presentation.port.in.MainInfoUseCase;
import com.samso.linkjoa.main.presentation.web.ResMain;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MainService implements MainInfoUseCase {

    private ClipRepository clipRepository;
    private TotalShareRepository totalShareRepository;
    @Override
    public ResMain getTotalInfo() {

        //TODO clipCount 조회
        long clipCount = clipRepository.count();
        TotalShare totalShare = totalShareRepository.findById(1L).get();

        //TODO shareCount 조회
        return ResMain.builder()
                .clipCount(clipCount)
                .shareCount(totalShare.getTotalCount())
                .build();
    }
}
