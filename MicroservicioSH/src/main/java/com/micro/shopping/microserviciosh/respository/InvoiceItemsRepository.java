package com.micro.shopping.microserviciosh.respository;

import com.micro.shopping.microserviciosh.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem, Long> {
}
