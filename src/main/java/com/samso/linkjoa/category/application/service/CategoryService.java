package com.samso.linkjoa.category.application.service;

import com.samso.linkjoa.category.application.out.repository.CategoryRepository;
import com.samso.linkjoa.category.domain.CategoryEnum;
import com.samso.linkjoa.category.presentation.port.in.EditCategoryInfoUseCase;
import com.samso.linkjoa.category.presentation.port.in.GetCategoryInfoUseCase;
import com.samso.linkjoa.category.presentation.web.request.ReqCategory;
import com.samso.linkjoa.category.presentation.web.response.ResCategory;
import com.samso.linkjoa.category.domain.entity.Category;
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
public class CategoryService implements GetCategoryInfoUseCase, EditCategoryInfoUseCase {

    private JwtUtil jwtUtil;
    private CategoryRepository categoryRepository;
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

        List<Category> editCategoryList = IntStream.range(0, reqCategoryList.size())
                            .mapToObj(index -> {
                                     return Category.builder()
                                             .id(reqCategoryList.get(index).getId())
                                             .name(reqCategoryList.get(index).getName())
                                             .color(reqCategoryList.get(index).getColor())
                                             .sortOrder(index).member(member)
                                             .build();
                            }).collect(Collectors.toList());

        Optional.ofNullable(categoryRepository.saveAll(editCategoryList))
                .orElseThrow(() -> new ApplicationInternalException(
                                        CategoryEnum.EDIT_CATEGORY_FAIL.getValue(),"Fail to edit category"));

        return CategoryEnum.EDIT_CATEGORY_SUCCESS.getValue();
    }
}
