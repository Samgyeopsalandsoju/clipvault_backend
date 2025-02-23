package com.samso.linkjoa.main.application.port.out;

import com.samso.linkjoa.main.domain.entity.TotalShare;

import java.util.Optional;

public interface TotalShareRepository {

    Optional<TotalShare> findById(long id);
}
