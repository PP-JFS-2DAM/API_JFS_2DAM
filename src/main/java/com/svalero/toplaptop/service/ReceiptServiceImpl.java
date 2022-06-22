package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Computer;
import com.svalero.toplaptop.domain.Order;
import com.svalero.toplaptop.domain.Receipt;
import com.svalero.toplaptop.domain.dto.ReceiptDTO;
import com.svalero.toplaptop.exception.OrderNotFoundException;
import com.svalero.toplaptop.exception.ReceiptNotFoundException;
import com.svalero.toplaptop.repository.OrderRepository;
import com.svalero.toplaptop.repository.ReceiptRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private OrderRepository orderRepository;

    public Flux<Receipt> findAll() {
        return receiptRepository.findAll();
    }

    public Mono<Receipt> findById(long id) throws ReceiptNotFoundException {
        return receiptRepository.findById(id).onErrorReturn(new Receipt());
    }

    @Override
    public Mono<Receipt> addReceipt(ReceiptDTO receiptDTO) throws OrderNotFoundException {
        Mono<Order> order = orderRepository.findById(receiptDTO.getOrder()).onErrorReturn(new Order());
        ModelMapper mapper = new ModelMapper();
        Receipt receipt = mapper.map(receiptDTO, Receipt.class);

        receipt.setOrder(order.block());



        return receiptRepository.save(receipt);
    }

    public Mono<Receipt> deleteReceipt(long id) throws ReceiptNotFoundException {
        Mono<Receipt> receipt = receiptRepository.findById(id).onErrorReturn(new Receipt());


        receiptRepository.delete(receipt.block());
        return receipt;
    }

    @Override
    public Mono<Receipt> modifyReceipt(long id, ReceiptDTO receiptDTO) throws ReceiptNotFoundException, OrderNotFoundException {
        Mono<Receipt> receipt = receiptRepository.findById(id).onErrorReturn(new Receipt());
        Mono<Order> order = orderRepository.findById(receiptDTO.getOrder()).onErrorReturn(new Order());



        receipt.block().setOrder(order.block());


        return receiptRepository.save(receipt.block());
    }
}
