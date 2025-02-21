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

    private final JpaForkRepository jpaOrderRepository;

    @Override
    public Fork save(Fork newFork) { return jpaOrderRepository.save(newFork); }

    @Override
    public Optional<List<Fork>> findByMemberId(long memberId, Sort sort) {
        return jpaOrderRepository.findByMemberId(memberId, sort);
    }

    @Override
    @Modifying
    @Transactional
    public Optional<Integer> deleteByIdAndMemberId(long forkId, long memberId) {
        return jpaOrderRepository.deleteByIdAndMemberId(forkId, memberId)
                                .filter(f -> f > 0);
    }
}
