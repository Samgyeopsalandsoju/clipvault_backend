package com.samso.linkjoa.clip.application.service;

import com.samso.linkjoa.category.application.out.repository.CategoryRepository;
import com.samso.linkjoa.category.domain.entity.Category;
import com.samso.linkjoa.category.presentation.web.request.ReqCategory;
import com.samso.linkjoa.clip.application.port.out.repository.ClipRepository;
import com.samso.linkjoa.clip.domain.ClipEnum;
import com.samso.linkjoa.clip.domain.entity.Clip;
import com.samso.linkjoa.clip.presentation.port.in.CreateClipUseCase;
import com.samso.linkjoa.clip.presentation.port.in.DeleteClipUseCase;
import com.samso.linkjoa.clip.presentation.port.in.GetClipInfoUseCase;
import com.samso.linkjoa.clip.presentation.port.in.ModifyClipUseCase;
import com.samso.linkjoa.clip.presentation.web.request.ReqClip;
import com.samso.linkjoa.clip.presentation.web.response.ResClip;
import com.samso.linkjoa.core.common.ApplicationInternalException;
import com.samso.linkjoa.core.springSecurity.JwtUtil;
import com.samso.linkjoa.domain.member.Member;
import io.jsonwebtoken.lang.Assert;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClipService implements CreateClipUseCase, GetClipInfoUseCase, ModifyClipUseCase, DeleteClipUseCase {

    private JwtUtil jwtUtil;
    private CategoryRepository categoryRepository;
    private ClipRepository clipRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private ModelMapper modelMapper;
    private static final int PAGE_SIZE = 30;

    @Override
    public List<ResClip> findRandomPublicClips(int pageSize, String visible) {
        int totalCount = clipRepository.countByVisible(visible);

        int randomStartIndex = (totalCount > pageSize) ?
                ThreadLocalRandom.current().nextInt(0, (int) (totalCount - pageSize) + 1) : 0;

        List<ResClip> publicClips = clipRepository.findPublicClipWithOffset(pageSize, randomStartIndex)
                .stream()
                .map(clip -> modelMapper.map(clip, ResClip.class))
                .collect(Collectors.toList());

        Collections.shuffle(publicClips);

        return publicClips;
    }
    @Override
    public List<ResClip> findRandomPublicClips(String visible) {

        int totalCount = clipRepository.countByVisible(visible);

        int randomStartIndex = (totalCount > PAGE_SIZE) ?
                ThreadLocalRandom.current().nextInt(0, (int) (totalCount - PAGE_SIZE) + 1) : 0;

        List<ResClip> publicClips = clipRepository.findPublicClipWithOffset(PAGE_SIZE, randomStartIndex)
                .stream()
                .map(clip -> modelMapper.map(clip, ResClip.class))
                .collect(Collectors.toList());

        Collections.shuffle(publicClips);

        return publicClips;
    }

    @Override
    @Transactional
    public String createClip(HttpServletRequest request, ReqClip reqClip) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);

        Member member = entityManager.getReference(Member.class, memberId);

        Category category = processCategory(reqClip.getCategory(), member);

        Clip clip = Clip.builder()
                .title(reqClip.getTitle())
                .link(reqClip.getLink())
                .visible(reqClip.getVisible())
                .forkedCount(0L)
                .category(category)
                .build();

        clipRepository.save(clip);
        return ClipEnum.CREATE_CLIP_SUCCESS.getValue();
    }

    private Category processCategory(ReqCategory reqCategory, Member member) {

        Optional<Category> optionalCategory = Optional.ofNullable(
                                                entityManager.find(Category.class, reqCategory.getId())
                                                );

        return optionalCategory.orElseGet(() -> {
            Category requestCategory = Category.builder()
                    .id(reqCategory.getId())
                    .name(reqCategory.getName())
                    .color(reqCategory.getColor())
                    .member(member)
                    .build();
            return categoryRepository.save(requestCategory);
        });
    }

    @Override
    public List<ResClip> getClipList(HttpServletRequest request) {
        long memberId = jwtUtil.getMemberIdFromRequest(request);

        return clipRepository.findByCategoryMemberId(memberId)
                .get()
                .stream()
                .map(clips -> modelMapper.map(clips, ResClip.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public String modifyClip(ReqClip reqClip) {

        Clip clip = entityManager.find(Clip.class, reqClip.getId());
        Assert.notNull(clip, ClipEnum.CLIP_EMPTY.getValue());

        clip.modifyClip(reqClip.getTitle()
                        , reqClip.getLink()
                        , reqClip.getVisible());

        return ClipEnum.MODIFY_CLIP_SUCCESS.getValue();
    }

    @Override
    public ResClip getClipById(HttpServletRequest request, Long clipId) {
        long memberId = jwtUtil.getMemberIdFromRequest(request);

        Clip clip = clipRepository.findByIdAndCategory_Member_Id(clipId, memberId)
                .orElseThrow(() -> new ApplicationInternalException(ClipEnum.NOT_FOUND_CLIP.getValue(), "Not found modify clip"));
        return modelMapper.map(clip, ResClip.class);
    }

    @Transactional
    @Override
    public String deleteClipById(HttpServletRequest request, Long clipId) {
        long memberId = jwtUtil.getMemberIdFromRequest(request);

        int deleteCount = clipRepository.deleteByIdAndMemberId(clipId, memberId);

        Optional.of(deleteCount)
                .filter(count -> count > 0)
                .orElseThrow(() -> new ApplicationInternalException(ClipEnum.DELETE_FAIL.getValue(), "Fail clip Delete"));

        return ClipEnum.DELETE_SUCCESS.getValue();
    }
}
