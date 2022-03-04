package com.lrcv.helpdesk.modules.technical.services;

import java.util.Optional;

import javax.validation.Valid;

import com.lrcv.helpdesk.modules.person.domain.models.Person;
import com.lrcv.helpdesk.modules.person.domain.repositories.PersonRepository;
import com.lrcv.helpdesk.modules.technical.domain.models.Technical;
import com.lrcv.helpdesk.modules.technical.domain.models.dtos.TechnicalDTO;
import com.lrcv.helpdesk.modules.technical.domain.repositories.TechnicalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdateTechnicalService {

    @Autowired
    private TechnicalRepository repository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Technical execute(Integer id, @Valid TechnicalDTO technicalDTO) {
        technicalDTO.setId(id);
        technicalDTO.setPassword(encoder.encode(technicalDTO.getPassword()));

        Optional<Person> person = personRepository.findByCpf(technicalDTO.getCpf());

        if (person.isPresent() && (person.get().getId() != technicalDTO.getId())) {
            throw new Error("CPF already registered");
        }

        person = personRepository.findByEmail(technicalDTO.getEmail());

        if (person.isPresent() && (person.get().getId() != technicalDTO.getId())) {
            throw new Error("Email already registered");
        }

        Technical technical = repository.findById(id).orElseThrow(() -> new Error("Technical not found"));

        technical = new Technical(technicalDTO);

        return repository.save(technical);
    }
}
