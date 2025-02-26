package com.samso.linkjoa.clip.domain.entity;

import com.samso.linkjoa.category.domain.entity.Category;
import com.samso.linkjoa.clip.presentation.web.request.ReqClip;
import io.netty.util.internal.StringUtil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="clip")
@EntityListeners(AuditingEntityListener.class)
public class Clip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;
    @Column(nullable = false, length = 255)
    private String link;
    @Column(nullable = false, length = 255)
    private String visible;
    @Column(nullable = false)
    private Long forkedCount;

    @Column(nullable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:sss")
    private LocalDateTime created_date;

    @Column(nullable = false)
    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:sss")
    private LocalDateTime modified_date;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;

    public void modifyClip(String title, String link, String visible){
        this.title = StringUtil.isNullOrEmpty(title) ? this.title : title;
        this.link = StringUtil.isNullOrEmpty(link) ? this.link : link;
        this.visible = StringUtil.isNullOrEmpty(visible) ? this.visible : visible;
    }
}
