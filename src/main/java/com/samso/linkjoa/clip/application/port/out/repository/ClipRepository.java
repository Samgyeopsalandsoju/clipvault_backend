package com.samso.linkjoa.clip.application.port.out.repository;

import com.samso.linkjoa.clip.domain.entity.Clip;

import java.util.List;
import java.util.Optional;

public interface ClipRepository {
    Clip save(Clip clip);

    Optional<Clip> findById(long clipId);
    Optional<List<Clip>> findByCategoryMemberId(Long memberId);

    Optional<Clip> findByIdAndCategory_Member_Id(Long clipId, Long memberId);

    int deleteByIdAndMemberId(long clipId, long memberId);
}
