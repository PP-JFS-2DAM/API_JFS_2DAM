package com.svalero.toplaptop.repository;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.domain.Receipt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
}
