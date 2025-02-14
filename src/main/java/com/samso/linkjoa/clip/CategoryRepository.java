package com.samso.linkjoa.clip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Category save(Category category);

    List<Category> findByMemberId(long memberId);
}
