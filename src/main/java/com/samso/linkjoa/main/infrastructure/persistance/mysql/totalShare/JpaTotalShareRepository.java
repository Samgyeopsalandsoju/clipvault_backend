package com.samso.linkjoa.main.infrastructure.persistance.mysql.totalShare;

import com.samso.linkjoa.main.domain.entity.TotalShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTotalShareRepository extends JpaRepository<TotalShare, Long> {

}
