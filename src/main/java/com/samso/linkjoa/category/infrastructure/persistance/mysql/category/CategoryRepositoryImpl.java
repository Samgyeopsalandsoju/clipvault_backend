package com.samso.linkjoa.category.infrastructure.persistance.mysql.category;

import com.samso.linkjoa.category.application.out.repository.CategoryRepository;
import com.samso.linkjoa.category.domain.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;

    @Override
    public Category save(Category category) {
        return jpaCategoryRepository.save(category);
    }

    @Override
    public List<Category> findByMemberId(long memberId) {
        return jpaCategoryRepository.findByMemberId(memberId);
    }
}
