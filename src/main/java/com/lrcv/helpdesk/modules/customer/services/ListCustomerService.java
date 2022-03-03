package com.lrcv.helpdesk.modules.customer.services;

import java.util.List;

import com.lrcv.helpdesk.modules.customer.domain.models.Customer;
import com.lrcv.helpdesk.modules.customer.domain.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListCustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> execute() {
        return repository.findAll();
    }
}
