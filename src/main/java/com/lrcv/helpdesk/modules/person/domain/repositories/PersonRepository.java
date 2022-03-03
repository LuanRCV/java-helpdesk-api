package com.lrcv.helpdesk.modules.person.domain.repositories;

import java.util.Optional;

import com.lrcv.helpdesk.modules.person.domain.models.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByCpf(String cpf);

    Optional<Person> findByEmail(String email);
}
