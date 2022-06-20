package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Technical;
import com.svalero.toplaptop.exception.OrderNotFoundException;
import com.svalero.toplaptop.exception.TechnicalNotFoundException;

import java.util.List;

public interface TechnicalService {




    List<Technical> findAll();

    Technical findById(long id) throws TechnicalNotFoundException;


    Technical addTechnical(TechnicalDTO technicalDTO) throws TechnicalNotFoundException;

    Technical deleteTechnical(long id) throws TechnicalNotFoundException;

    Technical modifyTechnical(long id, TechnicalDTO technicalDTO) throws TechnicalNotFoundException, OrderNotFoundException;

    Technical modifyName(long id, String name) throws TechnicalNotFoundException;





}
