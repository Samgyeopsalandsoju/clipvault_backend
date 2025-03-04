package com.samso.linkjoa.fork.infrastructure.persistance.mysql.fork;

import com.samso.linkjoa.fork.application.port.out.repository.ForkRepository;
import com.samso.linkjoa.fork.domain.entity.Fork;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ForkRepositoryImpl implements ForkRepository {

    private final JpaForkRepository jpaForkRepository;

    @Override
    public Fork save(Fork newFork) { return jpaForkRepository.save(newFork); }

    @Override
    public List<Long> findByMemberId(long memberId) {
        return jpaForkRepository.findByMemberId(memberId);
    }

    @Override
    public Optional<List<Fork>> findByMemberId(long memberId, Sort sort) {
        return jpaForkRepository.findByMemberId(memberId, sort);
    }

    @Override
    @Modifying
    @Transactional
    public Optional<Integer> deleteByIdAndMemberId(long forkId, long memberId) {
        return jpaForkRepository.deleteByIdAndMemberId(forkId, memberId)
                                .filter(f -> f > 0);
    }

    @Override
    public Optional<Fork> findByClipId(long clipId) {
        return jpaForkRepository.findByClipId(clipId);
    }
}
