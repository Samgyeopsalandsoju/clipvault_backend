package com.samso.linkjoa.clip;

import com.samso.linkjoa.domain.member.Member;
import io.jsonwebtoken.lang.Assert;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ClipService {

    private CategoryRepository categoryRepository;
    private ClipRepository clipRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private ModelMapper modelMapper;
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

    public List<ClipResponse> getClipListResponse(long memberId) {

        List<Clip> clipList = clipRepository.findByCategoryMemberId(memberId);
        return  clipList.stream()
                .map(clip -> modelMapper.map(clip, ClipResponse.class))
                .collect(Collectors.toList());
    }

    public List<CategoryResponse> getCategoryResponse(long memberId) {

        List<Category> categoryList = categoryRepository.findByMemberId(memberId);

        return categoryList.stream()
                .map(cate -> modelMapper.map(cate, CategoryResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public String modifyClip(ClipRequest clipRequest) {

        Clip clip = entityManager.find(Clip.class, clipRequest.getId());
        Assert.notNull(clip, ClipEnum.CLIP_EMPTY.getValue());

        clip.modifyClip(clipRequest.getTitle()
                        , clipRequest.getLink()
                        , clipRequest.getVisible());

        return ClipEnum.MODIFY_CLIP_SUCCESS.getValue();

    }
}
