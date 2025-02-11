package com.samso.linkjoa.category;

import com.samso.linkjoa.domain.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = false, nullable = false, length = 30)
    private String name;
    @Column(name = "color", nullable = false)
    private int color;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
