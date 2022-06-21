package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.domain.Receipt;
import com.svalero.toplaptop.domain.dto.ReceiptDTO;
import com.svalero.toplaptop.exception.OrderNotFoundException;
import com.svalero.toplaptop.exception.ReceiptNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ReceiptService {

    Flux<Receipt> findAll();

    Mono<Receipt> findById(long id) throws ReceiptNotFoundException;

    Mono<Receipt> addReceipt(ReceiptDTO receiptDTO) throws OrderNotFoundException;

    Mono<Receipt> deleteReceipt(long id) throws ReceiptNotFoundException;

    Mono<Receipt> modifyReceipt(long id, ReceiptDTO receiptDTO) throws ReceiptNotFoundException, OrderNotFoundException;

}
