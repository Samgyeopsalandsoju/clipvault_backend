package com.samso.linkjoa.clip;

import com.samso.linkjoa.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ClipService {

    private CategoryRepository categoryRepository;
    private ClipRepository clipRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public void createClip(ClipRequest clipRequest, Long memberId) {

        Member member = entityManager.getReference(Member.class, memberId);

        Category category = processCategory(clipRequest.getCategory(), member);
        Clip clip = Clip.builder()
                .title(clipRequest.getTitle())
                .link(clipRequest.getLink())
                .visible(clipRequest.getVisible())
                .forked_count(0L)
                .category(category)
                .build();

        clipRepository.save(clip);
    }

    private Category processCategory(CategoryRequest categoryRequest, Member member) {
        return Optional.ofNullable(categoryRequest.getId())
                .filter(id -> id !=0)
                .map(id -> entityManager.getReference(Category.class, id))
                .orElseGet(() ->{
                    Category requestCategory = Category.builder()
                            .name(categoryRequest.getName())
                            .color(categoryRequest.getColor())
                            .member(member)
                            .build();
                    return categoryRepository.save(requestCategory);
                });
    }
}
