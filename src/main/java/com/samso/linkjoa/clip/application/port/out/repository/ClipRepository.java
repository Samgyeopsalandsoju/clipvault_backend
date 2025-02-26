package com.samso.linkjoa.clip.application.port.out.repository;

import com.samso.linkjoa.category.domain.entity.Category;
import com.samso.linkjoa.clip.domain.entity.Clip;

import java.util.List;
import java.util.Optional;

public interface ClipRepository {

    long count();
    int countByVisible(String visible);
    List<Clip> findPublicClipWithOffset(int limit, int offset);
    Clip save(Clip clip);
    Optional<Clip> findById(long clipId);
    Optional<List<Clip>> findByCategoryMemberId(Long memberId);

    Optional<Clip> findByIdAndCategory_Member_Id(Long clipId, Long memberId);

    int deleteByIdAndMemberId(long clipId, long memberId);

    List<Clip> findByCategoryId(String categoryId);

    void deleteAll(List<Clip> clips);
    void flush();
}
