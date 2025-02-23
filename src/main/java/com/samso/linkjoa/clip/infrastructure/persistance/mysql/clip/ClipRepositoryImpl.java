package com.samso.linkjoa.clip.infrastructure.persistance.mysql.clip;

import com.samso.linkjoa.clip.application.port.out.repository.ClipRepository;
import com.samso.linkjoa.clip.domain.entity.Clip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClipRepositoryImpl implements ClipRepository {

    private final JpaClipRepository jpaClipRepository;

    @Override
    public long count() { return jpaClipRepository.count(); }

    @Override
    public int countByVisible(String visible) {
        return jpaClipRepository.countByVisible(visible);
    }

    @Override
    public List<Clip> findPublicClipWithOffset(int limit, int offset) {
        return jpaClipRepository.findPublicClipWithOffset(limit, offset);
    }

    @Override
    public Clip save(Clip clip) {
        return jpaClipRepository.save(clip);
    }

    @Override
    public Optional<Clip> findById(long clipId) {
        return jpaClipRepository.findById(clipId);
    }

    @Override
    public Optional<List<Clip>> findByCategoryMemberId(Long memberId) {
        return jpaClipRepository.findByCategoryMemberId(memberId);
    }

    @Override
    public Optional<Clip> findByIdAndCategory_Member_Id(Long clipId, Long memberId) {
        return jpaClipRepository.findByIdAndCategory_Member_Id(clipId, memberId);
    }

    @Override
    public int deleteByIdAndMemberId(long clipId, long memberId) {
        return jpaClipRepository.deleteByIdAndMemberId(clipId, memberId);
    }
}
