package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Technical;
import com.svalero.toplaptop.exception.TechnicalNotFoundException;
import com.svalero.toplaptop.repository.TechnicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicalServiceImpl implements TechnicalService {

    @Autowired
    private TechnicalRepository technicalRepository;

    public List<Technical> findAll() {
        return technicalRepository.findAll();
    }

    public Technical findById(long id) throws TechnicalNotFoundException {
        return technicalRepository.findById(id).orElseThrow(TechnicalNotFoundException::new);
    }

    @Override
    public Technical addTechnical(Technical technical) {
        return technicalRepository.save(technical);
    }

    public Technical deleteTechnical(long id) throws TechnicalNotFoundException {
        Technical technical = technicalRepository.findById(id).orElseThrow(TechnicalNotFoundException::new);

        technicalRepository.delete(technical);
        return technical;
    }

    @Override
    public Technical modifyTechnical(long id, Technical technical) throws TechnicalNotFoundException {
        technicalRepository.findById(id).orElseThrow(TechnicalNotFoundException::new);

        technical.setId(id);
        technicalRepository.save(technical);

        return technical;
    }
}
