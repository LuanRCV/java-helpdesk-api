package com.lrcv.helpdesk.modules.customer.services;

import com.lrcv.helpdesk.modules.customer.domain.models.Customer;
import com.lrcv.helpdesk.modules.customer.domain.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCustomerService {

    @Autowired
    private CustomerRepository repository;

    public void execute(Integer id) {
        Customer customer = repository.findById(id).orElseThrow(() -> new Error("Customer not found"));

        if (customer.getCalls().size() > 0) {
            throw new Error("Customer have opened calls and can't bem deleted");
        }
        
        repository.deleteById(id);
    }
}
