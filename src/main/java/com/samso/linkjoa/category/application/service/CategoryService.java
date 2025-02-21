package com.samso.linkjoa.category.application.service;

import com.samso.linkjoa.category.application.out.repository.CategoryRepository;
import com.samso.linkjoa.category.presentation.port.in.GetCategoryInfoUseCase;
import com.samso.linkjoa.category.presentation.web.response.CategoryResponse;
import com.samso.linkjoa.category.domain.entity.Category;
import com.samso.linkjoa.core.springSecurity.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryService implements GetCategoryInfoUseCase {

    private JwtUtil jwtUtil;
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    @Override
    public List<CategoryResponse> getCategoryList(HttpServletRequest request) {

        long memberId = jwtUtil.getMemberIdFromRequest(request);

        List<Category> categoryList = categoryRepository.findByMemberId(memberId);

        return categoryList.stream()
                .map(cate -> modelMapper.map(cate, CategoryResponse.class))
                .collect(Collectors.toList());
    }
}
