package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Order;
import com.svalero.toplaptop.domain.Technical;
import com.svalero.toplaptop.exception.TechnicalNotFoundException;
import com.svalero.toplaptop.repository.OrderRepository;
import com.svalero.toplaptop.repository.TechnicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicalServiceImpl implements ComputerService {




    @Autowired
    private TechnicalRepository technicalRepository;



    public List<Technical> findAll() {
        return technicalRepository.findAll();
    }

    public Technical findById(long id) throws TechnicalNotFoundException {
        return technicalRepository.findById(id).orElseThrow(TechnicalNotFoundException::new);
    }

    public Technical addTechnical(Technical technical) throws TechnicalNotFoundException {
        return technicalRepository.save(technical);
    }

    public Technical deleteTechnical(long id) throws TechnicalNotFoundException {
        Technical technical = technicalRepository.findById(id).orElseThrow(TechnicalNotFoundException::new);

        technicalRepository.delete(technical);
        return technical;
    }

    public Technical modifyTechnical(long id, Technical technical) throws TechnicalNotFoundException {
        technicalRepository.findById(id).orElseThrow(TechnicalNotFoundException::new);
        newTechnical.setId(id);
        technicalRepository.save(newTechnical);
        return newTechnical;
    }

    public Technical modifyName(long id, String name) throws TechnicalNotFoundException {
        Technical technical = technicalRepository.findById(id)
                .orElseThrow(TechnicalNotFoundException::new);
        technical.setName(name);
        return technicalRepository.save(technical);
    }






}
