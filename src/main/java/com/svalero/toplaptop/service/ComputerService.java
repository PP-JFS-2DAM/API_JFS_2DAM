package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.domain.dto.ComputerDTO;
import com.svalero.toplaptop.exception.ComputerNotFoundException;
import com.svalero.toplaptop.exception.UserNotFoundException;

import java.util.List;

public interface ComputerService {

    List<Computer> findAll();

    Computer findById(long id) throws ComputerNotFoundException;

    Computer addComputer(ComputerDTO computerDTO) throws UserNotFoundException;

    Computer deleteComputer(long id) throws ComputerNotFoundException;

    Computer modifyComputer(long id, ComputerDTO computerDTO) throws ComputerNotFoundException, UserNotFoundException;

}
