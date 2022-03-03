package com.lrcv.helpdesk.modules.technical.services;

import java.util.List;

import com.lrcv.helpdesk.modules.technical.domain.models.Technical;
import com.lrcv.helpdesk.modules.technical.domain.repositories.TechnicalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListTechnicalService {

    @Autowired
    private TechnicalRepository repository;

    public List<Technical> execute() {
        return repository.findAll();
    }
}
