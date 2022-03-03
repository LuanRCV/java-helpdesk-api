package com.lrcv.helpdesk.modules.person.domain.repositories;

import com.lrcv.helpdesk.modules.person.domain.models.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {}