package com.samso.linkjoa.main.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="total_share")
public class TotalShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long totalCount;

    public void addTotalShareCount(TotalShare totalShare){
        long totalCount = totalShare.getTotalCount();
        totalShare.setTotalCount(++totalCount);
    }
}
