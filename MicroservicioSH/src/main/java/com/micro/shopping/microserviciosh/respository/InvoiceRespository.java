package com.micro.shopping.microserviciosh.respository;

import com.micro.shopping.microserviciosh.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRespository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByCustomerId(Long customerId);
    Invoice findByNumberInvoice(String numberInvoice);
}
