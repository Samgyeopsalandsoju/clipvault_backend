package com.samso.linkjoa.category.application.out.repository;

import com.samso.linkjoa.category.domain.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Category save(Category category);
    List<Category> findByMemberIdOrderBySortOrderAsc(long memberId);

    List<Category> saveAll(List<Category> editCategoryList);

    Optional<Integer> deleteByIdAndMemberId(String categoryId, long memberId);
    Optional<Category> findById(String id);
}
