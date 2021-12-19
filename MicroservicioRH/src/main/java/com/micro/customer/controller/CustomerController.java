package com.micro.customer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.customer.entity.Customer;
import com.micro.customer.entity.Region;
import com.micro.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listAllCustomers(@RequestParam(required = false) Long regionId){
        List<Customer> customers= new ArrayList<>();

        if (regionId == null) {
            customers = customerService.findCustomerAll();
            if (customers.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }else{
            Region region = new Region();
            region.setId(regionId);

            customers = customerService.findCustomersByRegion(region);
            if (customers == null) {
                log.error("Customer with Region id {} not found",regionId);
                return ResponseEntity.notFound().build();
            }
        }
        return  ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        log.info("Fecth Customer with id {}");
        Customer customer = customerService.getCostumer(id);
        if (customer == null) {
            log.info("Customer with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result){
        log.info("Creating Customer: {}",customer);
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
         Customer customerDb = customerService.createCostumer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        log.info("Updaiting customer whit customer id {}", id);

        Customer currentCustomer = customerService.getCostumer(id);
        if (currentCustomer == null) {
            log.error("Unable to update, customer with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        customer.setId(id);
        currentCustomer = customerService.updateCostumer(customer);
        return ResponseEntity.ok(currentCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id){
        log.info("Fetching & Deleting customer with id {}", id);
        Customer customer = customerService.getCostumer(id);

        if (customer == null) {
            log.error("Unable to delete, customer with id {} not found", id);
            return ResponseEntity.notFound().build();
        }

        customer = customerService.deleteCostumer(customer);
        return ResponseEntity.ok(customer);
    }

    private String formatMessage(BindingResult result){
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();

        String jsonString ="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonString;
    }
}
