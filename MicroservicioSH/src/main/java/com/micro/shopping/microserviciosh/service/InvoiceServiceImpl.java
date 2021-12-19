package com.micro.shopping.microserviciosh.service;

import com.micro.shopping.microserviciosh.entity.Invoice;
import com.micro.shopping.microserviciosh.respository.InvoiceRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    InvoiceRespository invoiceRespository;

    @Override
    public List<Invoice> findInvoiceAll() {
        return invoiceRespository.findAll();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {

        Invoice invoiceDb = invoiceRespository.findByNumberInvoice(invoice.getNumberInvoice());
        if (invoiceDb != null) {
            return invoiceDb;
        }
        invoice.setState("CREATED");
        return invoiceRespository.save(invoice);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceDb = getInvoice(invoice.getId());
        if (invoiceDb == null) {
            return null;
        }
        invoiceDb.setCustomerId(invoice.getCustomerId());
        invoiceDb.setDescription(invoice.getDescription());
        invoiceDb.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDb.getItems().clear();
        invoiceDb.setItems(invoice.getItems());
        return invoiceRespository.save(invoiceDb);
    }

    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceDb = getInvoice(invoice.getId());
        if (invoiceDb == null) {
            return null;
        }
        invoiceDb.setState("DELETED");
        return invoiceRespository.save(invoiceDb);
    }

    @Override
    public Invoice getInvoice(Long id) {
        return invoiceRespository.findById(id).orElse(null);
    }
}
