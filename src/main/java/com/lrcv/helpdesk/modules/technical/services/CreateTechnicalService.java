package com.lrcv.helpdesk.modules.technical.services;

import java.util.Optional;

import com.lrcv.helpdesk.modules.person.domain.models.Person;
import com.lrcv.helpdesk.modules.person.domain.repositories.PersonRepository;
import com.lrcv.helpdesk.modules.technical.domain.models.Technical;
import com.lrcv.helpdesk.modules.technical.domain.models.dtos.TechnicalDTO;
import com.lrcv.helpdesk.modules.technical.domain.repositories.TechnicalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTechnicalService {

    @Autowired
    private TechnicalRepository repository;

    @Autowired
    private PersonRepository personRepository;

    public Technical execute(TechnicalDTO technicalDTO) {
        technicalDTO.setId(null);

        Optional<Person> person = personRepository.findByCpf(technicalDTO.getCpf());

        if (person.isPresent() && (person.get().getId() != technicalDTO.getId())) {
            throw new Error("CPF already registered");
        }

        person = personRepository.findByEmail(technicalDTO.getEmail());

        if (person.isPresent() && (person.get().getId() != technicalDTO.getId())) {
            throw new Error("Email already registered");
        }

        Technical technical = new Technical(technicalDTO);

        return repository.save(technical);
    }
}
