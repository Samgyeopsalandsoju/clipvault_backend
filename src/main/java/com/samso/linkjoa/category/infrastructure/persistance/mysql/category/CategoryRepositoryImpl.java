package com.samso.linkjoa.category.infrastructure.persistance.mysql.category;

import com.samso.linkjoa.category.application.port.out.repository.CategoryRepository;
import com.samso.linkjoa.category.domain.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;

    @Override
    public Category save(Category category) {
        return jpaCategoryRepository.save(category);
    }
    @Override
    public List<Category> findByMemberIdOrderBySortOrderAsc(long memberId) {
        return jpaCategoryRepository.findByMemberIdOrderBySortOrderAsc(memberId);
    }
    @Override
    public List<Category> saveAll(List<Category> editCategoryList) {
        return jpaCategoryRepository.saveAll(editCategoryList);
    }
    @Override
    public Optional<Integer> deleteByIdAndMemberId(String categoryId, long memberId) {
        return jpaCategoryRepository.deleteByIdAndMemberId(categoryId, memberId);
    }

    @Override
    public Optional<Category> findById(String id) {
        return jpaCategoryRepository.findById(id);
    }

    @Override
    public Optional<Integer> findMaxSortOrderByMemberId(Long memberId) {
        return jpaCategoryRepository.findMaxSortOrderByMemberId(memberId);
    }
    @Override
    public long countByMemberId(Long memberId) {
        return jpaCategoryRepository.countByMemberId(memberId);
    }
}
