package com.micro.shopping.microserviciosh.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Data
@Table(name = "tbl_invoice_items")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "El stock debe ser mayor que cero")
    private Double quantity;

    private Double price;

    @Column(name = "product_id")
    private Long productId;

    @Transient
    private Double subTotal;

    public Double getSubTotal(){
        if (price > 0 && quantity > 0) {
            return price*quantity;
        }else{
            return (double) 0;
        }
    }

    public InvoiceItem(){
        quantity = (double) 0;
        price = (double) 0;
    }
}
