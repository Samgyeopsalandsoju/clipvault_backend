package com.samso.linkjoa.share.infrastructure.persistance.mysql.share;

import com.samso.linkjoa.share.application.port.out.ShareRepository;
import com.samso.linkjoa.share.domain.entity.Share;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class ShareRepositoryImpl implements ShareRepository {

    private final JpaShareRepository jpaShareRepository;
    @Override
    public Share save(Share share) {
        return jpaShareRepository.save(share);
    }

    @Override
    public List<Share> findByDueAfterAndMemberId(LocalDateTime due, long memberId, Sort sort) {
        return jpaShareRepository.findByDueAfterAndMemberId(due, memberId, sort);
    }

    @Override
    public int deleteByIdAndMemberId(Long linkId, long memberId) {
        return jpaShareRepository.deleteByIdAndMemberId(linkId, memberId);
    }
}
