package com.samso.linkjoa.category;

import com.samso.linkjoa.core.common.ApplicationInternalException;
import com.samso.linkjoa.core.springSecurity.JwtUtil;
import com.samso.linkjoa.domain.member.Member;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.lang.Assert;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryUsecase {

    private JwtUtil jwtUtil;
    @PersistenceContext
    private EntityManager entityManager;
    private CategoryRepository categoryRepository;

    public CategoryCreateResponse createCategory(HttpServletRequest request, CategoryCreateRequest categoryCreateRequest) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);
        Member member = entityManager.getReference(Member.class, memberId);

        Category category = Category.builder()
                                .name(categoryCreateRequest.getName())
                                .color(categoryCreateRequest.getColor())
                                .member(member)
                                .build();
        Category savedCategory = categoryRepository.save(category);

        return CategoryCreateResponse.builder()
                    .id(savedCategory.getId())
                    .name(savedCategory.getName())
                    .color(savedCategory.getColor())
                    .build();
    }
}
