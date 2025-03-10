package com.samso.linkjoa.category.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.samso.linkjoa.clip.domain.entity.Clip;
import com.samso.linkjoa.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="category")
public class Category {

    @Id
    private String id;

    @Column(name = "name", unique = false, nullable = false, length = 30)
    private String name;
    @Column(name = "color", nullable = false)
    private int color;
    @Column(name = "sort_order", nullable = false)
    private int sortOrder;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Clip> clipList = new ArrayList<>();
}
