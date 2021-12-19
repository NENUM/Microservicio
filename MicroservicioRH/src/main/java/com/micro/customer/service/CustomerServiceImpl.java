package com.micro.customer.service;

import com.micro.customer.entity.Customer;
import com.micro.customer.entity.Region;
import com.micro.customer.respository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findCustomerAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findCustomersByRegion(Region region) {
        return customerRepository.findByRegion(region);
    }

    @Override
    public Customer createCostumer(Customer customer) {
        Customer customerDb = customerRepository.findByNumberID(customer.getNumberID());
        if (customerDb != null) {
            return customerDb;
        }

        customer.setState("CREATED");
        customerDb = customerRepository.save(customer);
        return customerDb;
    }

    @Override
    public Customer updateCostumer(Customer customer) {

        Customer customerDb = getCostumer(customer.getId());
        if (customerDb == null) {
            return null;
        }
        customerDb.setFirstName(customer.getFirstName());
        customerDb.setLastName(customer.getLastName());
        customerDb.setEmail(customer.getEmail());
        customerDb.setPhotoUrl(customer.getPhotoUrl());
        return customerRepository.save(customerDb);
    }

    @Override
    public Customer deleteCostumer(Customer customer) {
        Customer customerDb = getCostumer(customer.getId());
        if (customerDb == null) {
            return null;
        }
        customer.setState("DELETE");
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCostumer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}
