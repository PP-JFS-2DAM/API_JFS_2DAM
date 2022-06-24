package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.domain.dto.ComputerDTO;
import com.svalero.toplaptop.exception.ComputerNotFoundException;
import com.svalero.toplaptop.exception.UserNotFoundException;
import com.svalero.toplaptop.repository.ComputerRepository;
import com.svalero.toplaptop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerServiceImpl implements ComputerService {

    @Autowired
    private ComputerRepository computerRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Computer> findAll() {
        return computerRepository.findAll();
    }

    @Override
    public List<Computer> findAll(String brand, String model, String ram) {
        return computerRepository.findByBrandContainingOrModelContainingOrRamContaining(brand, model, ram);
    }

    public Computer findById(long id) throws ComputerNotFoundException {
        return computerRepository.findById(id).orElseThrow(ComputerNotFoundException::new);
    }

    @Override
    public Computer addComputer(ComputerDTO computerDTO) throws UserNotFoundException {
        ModelMapper mapper = new ModelMapper();
        Computer computer = mapper.map(computerDTO, Computer.class);

        computer.setUser(userRepository.findById(computerDTO.getUser())
                .orElseThrow(UserNotFoundException::new));

        computerRepository.save(computer);
        return computer;
    }

    public Computer deleteComputer(long id) throws ComputerNotFoundException {
        Computer computer = computerRepository.findById(id)
                .orElseThrow(ComputerNotFoundException::new);

        computerRepository.delete(computer);
        return computer;
    }

    @Override
    public Computer modifyComputer(long id, ComputerDTO computerDTO) throws ComputerNotFoundException, UserNotFoundException {
        computerRepository.findById(id).orElseThrow(ComputerNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Computer computer = mapper.map(computerDTO, Computer.class);

        computer.setId(id);
        computer.setUser(userRepository.findById(computerDTO.getUser())
                .orElseThrow(UserNotFoundException::new));

        computerRepository.save(computer);
        return computer;
    }
}
