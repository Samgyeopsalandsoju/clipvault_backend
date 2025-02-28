package com.samso.linkjoa.category.application.service;

import com.samso.linkjoa.category.application.out.repository.CategoryRepository;
import com.samso.linkjoa.category.domain.CategoryEnum;
import com.samso.linkjoa.category.presentation.port.in.DeleteCategoryInfoUseCase;
import com.samso.linkjoa.category.presentation.port.in.EditCategoryInfoUseCase;
import com.samso.linkjoa.category.presentation.port.in.GetCategoryInfoUseCase;
import com.samso.linkjoa.category.presentation.web.request.ReqCategory;
import com.samso.linkjoa.category.presentation.web.response.ResCategory;
import com.samso.linkjoa.category.domain.entity.Category;
import com.samso.linkjoa.clip.application.port.out.repository.ClipRepository;
import com.samso.linkjoa.clip.domain.entity.Clip;
import com.samso.linkjoa.core.common.ApplicationInternalException;
import com.samso.linkjoa.core.springSecurity.JwtUtil;
import com.samso.linkjoa.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
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
//        List<Category> editCategoryList = IntStream.range(0, reqCategoryList.size())
//                            .mapToObj(index -> {
//                                     return Category.builder()
//                                             .id(reqCategoryList.get(index).getId())
//                                             .name(reqCategoryList.get(index).getName())
//                                             .color(reqCategoryList.get(index).getColor())
//                                             .sortOrder(index).member(member)
//                                             .build();
//                            }).collect(Collectors.toList());

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
}
