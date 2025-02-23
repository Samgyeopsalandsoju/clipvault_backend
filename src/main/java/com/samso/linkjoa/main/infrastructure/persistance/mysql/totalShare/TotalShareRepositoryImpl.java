package com.samso.linkjoa.main.infrastructure.persistance.mysql.totalShare;

import com.samso.linkjoa.main.application.port.out.TotalShareRepository;
import com.samso.linkjoa.main.domain.entity.TotalShare;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TotalShareRepositoryImpl implements TotalShareRepository {

    private final JpaTotalShareRepository jpaTotalShareRepository;
    @Override
    public Optional<TotalShare> findById(long id) {
        return jpaTotalShareRepository.findById(id);
    }
}
