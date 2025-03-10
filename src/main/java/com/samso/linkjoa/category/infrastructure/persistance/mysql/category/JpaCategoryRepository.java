package com.samso.linkjoa.category.infrastructure.persistance.mysql.category;

import com.samso.linkjoa.category.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, String> {

    Category save(Category category);
    List<Category> findByMemberIdOrderBySortOrderAsc(long memberId);
    @Modifying
    @Query("delete from Category c where c.id = :id AND c.member.id = :memberId")
    Optional<Integer> deleteByIdAndMemberId(@Param("id") String categoryId, @Param("memberId") long memberId);
    @Query("select MAX(c.sortOrder) from Category c where c.member.id = :memberId")
    Optional<Integer> findMaxSortOrderByMemberId(@Param("memberId") Long memberId);
    long countByMemberId(Long memberId);
}
