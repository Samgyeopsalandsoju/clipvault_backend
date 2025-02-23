package com.samso.linkjoa.clip.application.service;

import com.samso.linkjoa.category.application.out.repository.CategoryRepository;
import com.samso.linkjoa.category.domain.entity.Category;
import com.samso.linkjoa.category.presentation.web.request.CategoryRequest;
import com.samso.linkjoa.category.presentation.web.response.CategoryResponse;
import com.samso.linkjoa.clip.application.port.out.repository.ClipRepository;
import com.samso.linkjoa.clip.domain.ClipEnum;
import com.samso.linkjoa.clip.domain.entity.Clip;
import com.samso.linkjoa.clip.presentation.port.in.CreateClipUseCase;
import com.samso.linkjoa.clip.presentation.port.in.DeleteClipUseCase;
import com.samso.linkjoa.clip.presentation.port.in.GetClipInfoUseCase;
import com.samso.linkjoa.clip.presentation.port.in.ModifyClipUseCase;
import com.samso.linkjoa.clip.presentation.web.request.ClipRequest;
import com.samso.linkjoa.clip.presentation.web.response.ClipResponse;
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
    public List<ClipResponse> findRandomPublicClips(int pageSize, String visible) {
        int totalCount = clipRepository.countByVisible(visible);

        int randomStartIndex = (totalCount > pageSize) ?
                ThreadLocalRandom.current().nextInt(0, (int) (totalCount - pageSize) + 1) : 0;

        List<ClipResponse> publicClips =   clipRepository.findPublicClipWithOffset(pageSize, randomStartIndex)
                .stream()
                .map(clip -> modelMapper.map(clip, ClipResponse.class))
                .collect(Collectors.toList());

        Collections.shuffle(publicClips);

        return publicClips;
    }
    @Override
    public List<ClipResponse> findRandomPublicClips(String visible) {

        int totalCount = clipRepository.countByVisible(visible);

        int randomStartIndex = (totalCount > PAGE_SIZE) ?
                ThreadLocalRandom.current().nextInt(0, (int) (totalCount - PAGE_SIZE) + 1) : 0;

        List<ClipResponse> publicClips = clipRepository.findPublicClipWithOffset(PAGE_SIZE, randomStartIndex)
                .stream()
                .map(clip -> modelMapper.map(clip, ClipResponse.class))
                .collect(Collectors.toList());

        Collections.shuffle(publicClips);

        return publicClips;
    }

    @Override
    @Transactional
    public String createClip(HttpServletRequest request, ClipRequest clipRequest) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);

        Member member = entityManager.getReference(Member.class, memberId);

        //TODO 도메인으로 분리
        Category category = processCategory(clipRequest.getCategory(), member);
        Clip clip = Clip.builder()
                .title(clipRequest.getTitle())
                .link(clipRequest.getLink())
                .visible(clipRequest.getVisible())
                .forkedCount(0L)
                .category(category)
                .build();

        clipRepository.save(clip);
        return ClipEnum.CREATE_CLIP_SUCCESS.getValue();
    }
//    public void createClip(ClipRequest clipRequest, Long memberId) {
//
//        Member member = entityManager.getReference(Member.class, memberId);
//
//        Category category = processCategory(clipRequest.getCategory(), member);
//        Clip clip = Clip.builder()
//                .title(clipRequest.getTitle())
//                .link(clipRequest.getLink())
//                .visible(clipRequest.getVisible())
//                .forked_count(0L)
//                .category(category)
//                .build();
//
//        clipRepository.save(clip);
//    }

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




    @Override
    public List<ClipResponse> getClipList(HttpServletRequest request) {
        long memberId = jwtUtil.getMemberIdFromRequest(request);

//        List<Clip> c = clipRepository.findByCategoryMemberId(memberId).get();
        return clipRepository.findByCategoryMemberId(memberId)
                .get()
                .stream()
                .map(clips -> modelMapper.map(clips, ClipResponse.class))
                .collect(Collectors.toList());
    }



//    public List<ClipResponse> getClipListResponse(long memberId) {
//
//        List<Clip> clipList = clipRepository.findByCategoryMemberId(memberId);
//        return  clipList.stream()
//                .map(clip -> modelMapper.map(clip, ClipResponse.class))
//                .collect(Collectors.toList());
//    }

//    public List<CategoryResponse> getCategoryResponse(long memberId) {
//
//        List<Category> categoryList = categoryRepository.findByMemberId(memberId);
//
//        return categoryList.stream()
//                .map(cate -> modelMapper.map(cate, CategoryResponse.class))
//                .collect(Collectors.toList());
//    }

    @Transactional
    @Override
    public String modifyClip(ClipRequest clipRequest) {

        Clip clip = entityManager.find(Clip.class, clipRequest.getId());
        Assert.notNull(clip, ClipEnum.CLIP_EMPTY.getValue());

        clip.modifyClip(clipRequest.getTitle()
                        , clipRequest.getLink()
                        , clipRequest.getVisible());

        return ClipEnum.MODIFY_CLIP_SUCCESS.getValue();
    }

    @Override
    public ClipResponse getClipById(HttpServletRequest request, Long clipId) {
        long memberId = jwtUtil.getMemberIdFromRequest(request);

        Clip clip = clipRepository.findByIdAndCategory_Member_Id(clipId, memberId)
                .orElseThrow(() -> new ApplicationInternalException(ClipEnum.NOT_FOUND_CLIP.getValue(), "Not found modify clip"));
        return modelMapper.map(clip, ClipResponse.class);
    }

//    public ClipResponse getClipById(Long clipId, Long memberId) {
//
//
//        Clip clip = clipRepository.findByIdAndCategory_Member_Id(clipId, memberId)
//                .orElseThrow(() -> new ApplicationInternalException(ClipEnum.NOT_FOUND_CLIP.getValue(), "Not found modify clip"));
//
//        return modelMapper.map(clip, ClipResponse.class);
//    }

//    public String deleteClipById(Long clipId, long memberId) {
//
//        int deleteCount = clipRepository.deleteByIdAndMemberId(clipId, memberId);
//
//        Optional.of(deleteCount)
//                .filter(count -> count > 0)
//                .orElseThrow(() -> new ApplicationInternalException(ClipEnum.DELETE_FAIL.getValue(), "Fail clip Delete"));
//
//        return ClipEnum.DELETE_SUCCESS.getValue();
//    }


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
