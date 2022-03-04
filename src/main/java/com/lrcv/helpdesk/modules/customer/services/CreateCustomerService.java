package com.lrcv.helpdesk.modules.customer.services;

import java.util.Optional;

import com.lrcv.helpdesk.modules.customer.domain.models.Customer;
import com.lrcv.helpdesk.modules.customer.domain.models.dtos.CustomerDTO;
import com.lrcv.helpdesk.modules.customer.domain.repositories.CustomerRepository;
import com.lrcv.helpdesk.modules.person.domain.models.Person;
import com.lrcv.helpdesk.modules.person.domain.repositories.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Customer execute(CustomerDTO customerDTO) {
        customerDTO.setId(null);
        customerDTO.setPassword(encoder.encode(customerDTO.getPassword()));

        Optional<Person> person = personRepository.findByCpf(customerDTO.getCpf());

        if (person.isPresent() && (person.get().getId() != customerDTO.getId())) {
            throw new Error("CPF already registered");
        }

        person = personRepository.findByEmail(customerDTO.getEmail());

        if (person.isPresent() && (person.get().getId() != customerDTO.getId())) {
            throw new Error("Email already registered");
        }

        Customer customer = new Customer(customerDTO);

        return repository.save(customer);
    }
}
