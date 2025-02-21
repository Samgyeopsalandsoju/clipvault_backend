package com.samso.linkjoa.category.application.out.repository;

import com.samso.linkjoa.category.domain.entity.Category;

import java.util.List;

public interface CategoryRepository {

    public Category save(Category category);
    List<Category> findByMemberId(long memberId);
}
