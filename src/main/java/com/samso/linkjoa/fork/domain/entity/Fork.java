package com.samso.linkjoa.fork.domain.entity;

import com.samso.linkjoa.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "fork")
@EntityListeners(AuditingEntityListener.class)
public class Fork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clip_title", nullable = false, length = 255)
    private String clipTitle;

    @Column(name = "clip_link", nullable = false, length = 255)
    private String clipLink;

    @Column(name = "category_name", nullable = false, length = 30)
    private String categoryName;

    @Column(name = "category_color", nullable = false, length = 255)
    private int categoryColor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "clip_id", nullable = false)
    private Long clipId;

    @Column(nullable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:sss")
    private LocalDateTime createdDate;

}
