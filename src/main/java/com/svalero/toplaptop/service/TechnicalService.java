package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Technical;
import com.svalero.toplaptop.exception.OrderNotFoundException;
import com.svalero.toplaptop.exception.TechnicalNotFoundException;

import java.util.List;

public interface TechnicalService {

    List<Technical> findAll();

    Technical findById(long id) throws TechnicalNotFoundException;

    Technical addTechnical(Technical technical);

    Technical deleteTechnical(long id) throws TechnicalNotFoundException;

    Technical modifyTechnical(long id, Technical technical) throws TechnicalNotFoundException;

}
