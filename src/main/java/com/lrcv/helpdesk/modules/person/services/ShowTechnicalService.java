package com.lrcv.helpdesk.modules.person.services;

import java.util.Optional;

import com.lrcv.helpdesk.modules.person.domain.models.Technical;
import com.lrcv.helpdesk.modules.person.domain.repositories.TechnicalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowTechnicalService {

    @Autowired
    private TechnicalRepository repository;

    public Technical execute(Integer id) {
        Optional<Technical> technical = repository.findById(id);

        return technical.orElseThrow(() -> new Error("Object not found, Id:" + id));
    }
}
