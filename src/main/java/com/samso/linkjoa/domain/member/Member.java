package com.samso.linkjoa.domain.member;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.samso.linkjoa.core.springSecurity.Role;
import com.samso.linkjoa.category.domain.entity.Category;
import com.samso.linkjoa.fork.domain.entity.Fork;
import com.samso.linkjoa.share.Share;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 255)
    private String mail;
    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:sss")
    private LocalDateTime sign_date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role = Role.USER;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Category> categoryList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Share> shareList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Fork> forkList = new ArrayList<>();

    public Member(String mail, String password, Role role){
        this.mail = mail;
        this.password = password;
        this.role = role;
    }
}
