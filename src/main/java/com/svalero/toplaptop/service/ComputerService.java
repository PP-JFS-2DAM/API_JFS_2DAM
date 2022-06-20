package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.exception.ComputerNotFoundException;

import java.util.List;

public interface ComputerService {





    List<Computer> findAll();

    Computer findById(long id) throws ComputerNotFoundException;

    Computer addComputer(Computer computer) throws ComputerNotFoundException;

    Computer deleteComputer(long id) throws ComputerNotFoundException;

    Computer modifyComputer(long id, Computer computer) throws ComputerNotFoundException;

    Computer modifyBrand(long id, String brand) throws ComputerNotFoundException;








}
