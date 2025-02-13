package com.samso.linkjoa.clip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClipRepository extends JpaRepository<Clip, Long> {

    public Clip save(Clip clip);

    @Query("SELECT c FROM Clip c Join FETCH c.category cate JOIN FETCH cate.member m WHERE m.id = :memberId ORDER BY c.modified_date DESC")
    List<Clip> findByCategoryMemberId(Long memberId);
}
