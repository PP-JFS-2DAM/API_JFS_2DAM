package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.exception.ComputerNotFoundException;
import com.svalero.toplaptop.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerServiceImpl implements ComputerService {



    @Autowired
    private ComputerRepository computerRepository;



    public List<Computer> findAll() {
        return computerRepository.findAll();
    }

    public Computer findById(long id) throws ComputerNotFoundException {
        return computerRepository.findById(id).orElseThrow(ComputerNotFoundException::new);
    }

    public Computer addComputer(Computer computer) throws ComputerNotFoundException {
        return computerRepository.save(computer);
    }

    public Computer deleteComputer(long id) throws ComputerNotFoundException {
        Computer computer = computerRepository.findById(id).orElseThrow(ComputerNotFoundException::new);

        computerRepository.delete(computer);
        return computer;
    }

    public Computer modifyComputer(long id, Computer computer) throws ComputerNotFoundException {
        computerRepository.findById(id).orElseThrow(ComputerNotFoundException::new);
        newComputer.setId(id);
        computerRepository.save(newComputer);
        return newComputer;
    }

    public Computer modifyBrand(long id, String brand) throws ComputerNotFoundException {
        Computer computer = computerRepository.findById(id)
                .orElseThrow(ComputerNotFoundException::new);
        computer.setBrand(brand);
        return computerRepository.save(computer);
    }
}
