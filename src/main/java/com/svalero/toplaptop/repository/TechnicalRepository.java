package com.svalero.toplaptop.repository;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.domain.Technical;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalRepository extends CrudRepository<Technical, Long> {
}
