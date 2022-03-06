package com.lrcv.helpdesk.modules.customer.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.lrcv.helpdesk.modules.customer.domain.models.Customer;
import com.lrcv.helpdesk.modules.customer.domain.models.dtos.CustomerDTO;
import com.lrcv.helpdesk.modules.customer.services.CreateCustomerService;
import com.lrcv.helpdesk.modules.customer.services.DeleteCustomerService;
import com.lrcv.helpdesk.modules.customer.services.ListCustomerService;
import com.lrcv.helpdesk.modules.customer.services.ShowCustomerService;
import com.lrcv.helpdesk.modules.customer.services.UpdateCustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private ListCustomerService listCustomerService;

    @Autowired
    private ShowCustomerService showCustomerService;

    @Autowired
    private CreateCustomerService createCustomerService;

    @Autowired
    private UpdateCustomerService updateCustomerService;

    @Autowired
    private DeleteCustomerService deleteCustomerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll() {
        List<Customer> customers = listCustomerService.execute();
        List<CustomerDTO> customersDTO = customers.stream().map(customer -> new CustomerDTO(customer))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(customersDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Integer id) {
        Customer customer = showCustomerService.execute(id);

        return ResponseEntity.ok().body(new CustomerDTO(customer));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = createCustomerService.execute(customerDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable Integer id,
            @Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = updateCustomerService.execute(id, customerDTO);

        return ResponseEntity.ok().body(new CustomerDTO(customer));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> delete(@PathVariable Integer id) {
        deleteCustomerService.execute(id);

        return ResponseEntity.noContent().build();
    }
}
