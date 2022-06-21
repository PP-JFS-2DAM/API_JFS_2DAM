package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.domain.User;
import com.svalero.toplaptop.domain.dto.ComputerDTO;
import com.svalero.toplaptop.exception.ComputerNotFoundException;
import com.svalero.toplaptop.exception.UserNotFoundException;
import com.svalero.toplaptop.repository.ComputerRepository;
import com.svalero.toplaptop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ComputerServiceImpl implements ComputerService {

    @Autowired
    private ComputerRepository computerRepository;
    @Autowired
    private UserRepository userRepository;

    public Flux<Computer> findAll() {
        return computerRepository.findAll();
    }

    public Mono<Computer> findById(long id) throws ComputerNotFoundException {
        return computerRepository.findById(id).onErrorReturn(new Computer());
    }

    @Override
    public Mono<Computer> addComputer(ComputerDTO computerDTO) throws UserNotFoundException {
        Mono<User> user = userRepository.findById(computerDTO.getUser()).onErrorReturn(new User());


        ModelMapper mapper = new ModelMapper();
        Computer computer = mapper.map(computerDTO, Computer.class);

        computer.setUser(user.block());


        return computerRepository.save(computer);
    }

    public Mono<Computer> deleteComputer(long id) throws ComputerNotFoundException {
        Mono<Computer> computer = computerRepository.findById(id).onErrorReturn(new Computer());

        computerRepository.delete(computer.block());
        return computer;
    }

    @Override
    public Mono<Computer> modifyComputer(long id, ComputerDTO computerDTO) throws ComputerNotFoundException, UserNotFoundException {
        Mono<Computer> computer = computerRepository.findById(id).onErrorReturn(new Computer());



        computer.block().setId(id);
      //  computer.block().setUser(userRepository.findById(computerDTO.getUser()));

        computerRepository.save(computer.block());
        return computer;
    }
}
