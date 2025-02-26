package com.samso.linkjoa.category.infrastructure.persistance.mysql.category;

import com.samso.linkjoa.category.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, Long> {

    Category save(Category category);
    List<Category> findByMemberIdOrderBySortOrderAsc(long memberId);
}
