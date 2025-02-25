package com.samso.linkjoa.fork.infrastructure.persistance.mysql.fork;

import com.samso.linkjoa.fork.domain.entity.Fork;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaForkRepository extends JpaRepository<Fork, Long> {

    Optional<List<Fork>> findByMemberId(long memberId, Sort sort);
    Optional<Integer> deleteByIdAndMemberId(long forkId, long memberId);
    Optional<Fork> findByClipId(long clipId);
}
