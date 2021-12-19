package com.micro.customer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "tbl_customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 10, max = 10, message = "El tamaño del numero debe ser 10")
    @Column(name = "number_id", unique = true, length = 10, nullable = false)
    private String numberID;

    @NotEmpty(message = "El nombre no puede estar vacio")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "El apellido no puede estar vacio")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "El correo no puede estar vacio")
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "photo_url")
    private String photoUrl;

    @NotNull(message = "La region no puede estar vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;

    private String state;
}
