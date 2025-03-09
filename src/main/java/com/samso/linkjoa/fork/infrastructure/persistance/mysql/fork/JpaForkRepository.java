package com.samso.linkjoa.fork.infrastructure.persistance.mysql.fork;

import com.samso.linkjoa.fork.domain.entity.Fork;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaForkRepository extends JpaRepository<Fork, Long> {

    @Query("SELECT f.clipId FROM Fork f WHERE f.member.id = :memberId")
    List<Long> findByMemberId(long memberId);
    Optional<List<Fork>> findByMemberId(long memberId, Sort sort);
    Optional<Integer> deleteByIdAndMemberId(long forkId, long memberId);
    Optional<Fork> findByClipIdAndMemberId(long clipId, long memberId);
    Long countByMemberId(Long member_id);
}
