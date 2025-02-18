package com.samso.linkjoa.fork.application.port.out.repository;

import com.samso.linkjoa.fork.domain.entity.Fork;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ForkRepository  {

    Fork save(Fork fork);

    Optional<List<Fork>> findByMemberId(long memberId, Sort sort);

}
