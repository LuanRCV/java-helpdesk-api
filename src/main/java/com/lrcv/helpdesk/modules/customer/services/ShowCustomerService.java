package com.lrcv.helpdesk.modules.customer.services;

import java.util.Optional;

import com.lrcv.helpdesk.modules.customer.domain.models.Customer;
import com.lrcv.helpdesk.modules.customer.domain.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowCustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer execute(Integer id) {
        Optional<Customer> customer = repository.findById(id);

        return customer.orElseThrow(() -> new Error("Object not found, Id:" + id));
    }
}
