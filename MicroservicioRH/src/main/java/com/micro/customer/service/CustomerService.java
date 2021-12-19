package com.micro.customer.service;

import com.micro.customer.entity.Customer;
import com.micro.customer.entity.Region;

import java.util.List;

public interface CustomerService {

    List<Customer> findCustomerAll();
    List<Customer> findCustomersByRegion(Region region);
    Customer createCostumer(Customer customer);
    Customer updateCostumer(Customer customer);
    Customer deleteCostumer(Customer customer);
    Customer getCostumer(Long id);
}
