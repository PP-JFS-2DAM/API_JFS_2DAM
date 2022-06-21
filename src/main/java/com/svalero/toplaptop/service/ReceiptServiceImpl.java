package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Receipt;
import com.svalero.toplaptop.domain.dto.ReceiptDTO;
import com.svalero.toplaptop.exception.OrderNotFoundException;
import com.svalero.toplaptop.exception.ReceiptNotFoundException;
import com.svalero.toplaptop.repository.OrderRepository;
import com.svalero.toplaptop.repository.ReceiptRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<Receipt> findAll() {
        return receiptRepository.findAll();
    }

    public Receipt findById(long id) throws ReceiptNotFoundException {
        return receiptRepository.findById(id).orElseThrow(ReceiptNotFoundException::new);
    }

    @Override
    public Receipt addReceipt(ReceiptDTO receiptDTO) throws OrderNotFoundException {
        ModelMapper mapper = new ModelMapper();
        Receipt receipt = mapper.map(receiptDTO, Receipt.class);

        receipt.setOrder(orderRepository.findById(receiptDTO.getOrder())
                .orElseThrow(OrderNotFoundException::new));

        receiptRepository.save(receipt);
        return receipt;
    }

    public Receipt deleteReceipt(long id) throws ReceiptNotFoundException {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(ReceiptNotFoundException::new);

        receiptRepository.delete(receipt);
        return receipt;
    }

    @Override
    public Receipt modifyReceipt(long id, ReceiptDTO receiptDTO) throws ReceiptNotFoundException, OrderNotFoundException {
        receiptRepository.findById(id).orElseThrow(ReceiptNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Receipt receipt = mapper.map(receiptDTO, Receipt.class);

        receipt.setId(id);
        receipt.setOrder(orderRepository.findById(receiptDTO.getOrder())
                .orElseThrow(OrderNotFoundException::new));

        receiptRepository.save(receipt);
        return receipt;
    }
}
