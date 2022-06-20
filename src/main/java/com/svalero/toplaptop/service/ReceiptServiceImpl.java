package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Order;
import com.svalero.toplaptop.domain.Receipt;
import com.svalero.toplaptop.exception.ReceiptNotFoundException;
import com.svalero.toplaptop.repository.OrderRepository;
import com.svalero.toplaptop.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {





    @Autowired
    private ReceiptRepository receiptRepository;



    public List<Receipt> findAll() {
        return receiptRepository.findAll();
    }

    public Receipt findById(long id) throws ReceiptNotFoundException {
        return receiptRepository.findById(id).orElseThrow(ReceiptNotFoundException::new);
    }

    public Receipt addReceipt(Receipt receipt) throws ReceiptNotFoundException {
        return receiptRepository.save(receipt);
    }

    public Receipt deleteReceipt(long id) throws ReceiptNotFoundException {
        Receipt receipt = receiptRepository.findById(id).orElseThrow(ReceiptNotFoundException::new);

        receiptRepository.delete(receipt);
        return receipt;
    }

    public Receipt modifyReceipt(long id, Receipt receipt) throws ReceiptNotFoundException {
        receiptRepository.findById(id).orElseThrow(ReceiptNotFoundException::new);
        newReceipt.setId(id);
        receiptRepository.save(newReceipt);
        return newReceipt;
    }

    public Receipt modifyPrice(long id, int price) throws ReceiptNotFoundException {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(ReceiptNotFoundException::new);
        receipt.setPrice(price);
        return receiptRepository.save(receipt);
    }





}
