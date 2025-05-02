package com.samso.linkjoa.category.application.service;

import com.samso.linkjoa.category.application.port.out.repository.CategoryRepository;
import com.samso.linkjoa.category.domain.CategoryEnum;
import com.samso.linkjoa.category.presentation.port.in.DeleteCategoryInfoUseCase;
import com.samso.linkjoa.category.presentation.port.in.EditCategoryInfoUseCase;
import com.samso.linkjoa.category.presentation.port.in.GetCategoryInfoUseCase;
import com.samso.linkjoa.category.presentation.web.request.ReqCategory;
import com.samso.linkjoa.category.presentation.web.response.ResCategory;
import com.samso.linkjoa.category.domain.entity.Category;
import com.samso.linkjoa.clip.application.port.out.repository.ClipRepository;
import com.samso.linkjoa.clip.domain.entity.Clip;
import com.samso.linkjoa.core.common.commonEnum.CreateLimitEnum;
import com.samso.linkjoa.core.common.exception.ApplicationInternalException;
import com.samso.linkjoa.core.springSecurity.JwtUtil;
import com.samso.linkjoa.member.domain.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
public class CategoryService implements GetCategoryInfoUseCase, EditCategoryInfoUseCase, DeleteCategoryInfoUseCase {

    private JwtUtil jwtUtil;
    private CategoryRepository categoryRepository;
    private ClipRepository clipRepository;
    private ModelMapper modelMapper;
    private EntityManager entityManager;
    @Override
    public List<ResCategory> getCategoryList(HttpServletRequest request) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);

        List<Category> categoryList = categoryRepository.findByMemberIdOrderBySortOrderAsc(memberId);

        return categoryList.stream()
                .map(cate -> modelMapper.map(cate, ResCategory.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String editCategoryListInfo(HttpServletRequest request, List<ReqCategory> reqCategoryList) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);
        Member member = entityManager.getReference(Member.class, memberId);

        if(reqCategoryList.size() > CreateLimitEnum.CATEGORY.getValue()){
            throw new ApplicationInternalException(String.valueOf(CreateLimitEnum.OVER.getValue()), "Over the maximum limit of Category");
        }

        List<Category> editCategoryList = IntStream.iterate(reqCategoryList.size() -1, i -> i>=0, i -> i-1)
                        .mapToObj(i ->{
                            ReqCategory reqCategory = reqCategoryList.get(i);
                            return Optional.ofNullable(reqCategory.getId())
                                    .flatMap(categoryRepository::findById)
                                    .map(existingCategory -> {
                                        existingCategory.setName(reqCategory.getName());
                                        existingCategory.setColor(reqCategory.getColor());
                                        existingCategory.setSortOrder(i);
                                        return existingCategory;
                                    })
                                    .orElseGet(() -> Category.builder()
                                            .id(reqCategory.getId())
                                            .name(reqCategory.getName())
                                            .color(reqCategory.getColor())
                                            .sortOrder(i)
                                            .member(member)
                                            .build()
                                    );
                        }).collect(Collectors.toList());

        Optional.ofNullable(categoryRepository.saveAll(editCategoryList))
                .orElseThrow(() -> new ApplicationInternalException(
                                        CategoryEnum.EDIT_CATEGORY_FAIL.getValue(),"Fail to edit category"));

        return CategoryEnum.EDIT_CATEGORY_SUCCESS.getValue();
    }

    @Override
    @Transactional
    public String deleteCategoryById(HttpServletRequest request, String categoryId) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);

        //클립 데이터 삭제
        List<Clip> clips = clipRepository.findByCategoryId(categoryId);
        clipRepository.deleteAll(clips);
        clipRepository.flush();
        //카테고리 삭제
        categoryRepository.deleteByIdAndMemberId(categoryId, memberId)
                .filter(count -> count > 0)
                .orElseThrow(() -> new ApplicationInternalException(
                                    CategoryEnum.DELETE_CATEGORY_INFO_EMPTY.getValue(), "Delete Data Empty"
                            ));
        return CategoryEnum.DELETE_CATEGORY_SUCCESS.getValue();
    }

    @Override
    @Transactional
    public String createCategory(HttpServletRequest request, ReqCategory reqCategory) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);
        Member member = entityManager.getReference(Member.class, memberId);

        long myCategoryCount  = categoryRepository.countByMemberId(member.getId());

        //카테고리 갯수 제한
        if(myCategoryCount >= CreateLimitEnum.CATEGORY.getValue()){
            throw new ApplicationInternalException(String.valueOf(CreateLimitEnum.OVER.getValue()), "Over the maximum limit of Category");
        }
        //정렬순서
        int sortOrder = findSortMaxOrder(member);
        Category createCategoryInfo = Category.builder()
                                    .id(reqCategory.getId())
                                    .name(reqCategory.getName())
                                    .color(reqCategory.getColor())
                                    .sortOrder(sortOrder)
                                    .member(member)
                                    .build();

        categoryRepository.save(createCategoryInfo);
        return CategoryEnum.CREATE_CATEGORY_SUCCESS.getValue();
    }

    @Override
    @Transactional
    public String modifyCategory(HttpServletRequest request, ReqCategory reqCategory) {

        Category categoryInfo = categoryRepository.findById(reqCategory.getId())
                .orElseThrow(() -> new ApplicationInternalException(CategoryEnum.NOT_FOUND_CATEGORY.getValue(), "Not Found Modify Category Info"));

        categoryInfo.modifyCategory(modelMapper.map(reqCategory, Category.class));

        return CategoryEnum.MODIFY_CATEGORY_SUCCESS.getValue();
    }
    private int findSortMaxOrder(Member member){
        return categoryRepository.findMaxSortOrderByMemberId(member.getId())
                .map(max -> ++max)
                .orElse(0);
    }
}
