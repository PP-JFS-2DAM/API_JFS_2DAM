package com.svalero.toplaptop.repository;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.domain.Receipt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends CrudRepository<Receipt, Long> {

    List<Receipt> findAll();

}
