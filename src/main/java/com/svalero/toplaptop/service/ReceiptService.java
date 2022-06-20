package com.svalero.toplaptop.service;

import com.svalero.toplaptop.domain.Receipt;
import com.svalero.toplaptop.exception.OrderNotFoundException;
import com.svalero.toplaptop.exception.ReceiptNotFoundException;

import java.util.List;

public interface ReceiptService {


    List<Receipt> findAll();



    Receipt findById(long id) throws ReceiptNotFoundException;



    Receipt addReceipt(ReceiptDTO receiptDTO) throws ReceiptNotFoundException;

    Receipt deleteReceipt(long id) throws ReceiptNotFoundException;

    Receipt modifyReceipt(long id, ReceiptDTO receiptDTO) throws ReceiptNotFoundException, OrderNotFoundException;

    Receipt modifyPrice(long id, int price) throws ReceiptNotFoundException;





}
