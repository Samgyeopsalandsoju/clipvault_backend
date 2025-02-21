package com.samso.linkjoa.clip.infrastructure.persistance.mysql.clip;

import com.samso.linkjoa.clip.domain.entity.Clip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaClipRepository extends JpaRepository<Clip, Long> {
    int countByVisible(String visible);

    @Query(value="SELECT * FROM clip WHERE visible = 'public' ORDER BY RAND() LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Clip> findPublicClipWithOffset(int limit, int offset);
    Clip save(Clip clip);
    @Query("SELECT c FROM Clip c Join FETCH c.category cate JOIN FETCH cate.member m WHERE m.id = :memberId ORDER BY c.modified_date DESC")
    Optional<List<Clip>> findByCategoryMemberId(Long memberId);
    Optional<Clip> findByIdAndCategory_Member_Id(Long clipId, Long memberId);
    @Modifying
    @Query(value = "DELETE t1 FROM clip AS t1 JOIN category AS t2 ON t1.category_id = t2.id WHERE t1.id = :clipId AND t2.member_id = :memberId",
            nativeQuery = true)
    int deleteByIdAndMemberId(Long clipId, long memberId);
}
