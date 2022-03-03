package com.lrcv.helpdesk.modules.customer.domain.repositories;

import com.lrcv.helpdesk.modules.customer.domain.models.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {}