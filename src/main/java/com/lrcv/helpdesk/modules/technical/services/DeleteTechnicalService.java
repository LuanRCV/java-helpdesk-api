package com.lrcv.helpdesk.modules.technical.services;

import com.lrcv.helpdesk.modules.technical.domain.models.Technical;
import com.lrcv.helpdesk.modules.technical.domain.repositories.TechnicalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTechnicalService {

    @Autowired
    private TechnicalRepository repository;

    public void execute(Integer id) {
        Technical technical = repository.findById(id).orElseThrow(() -> new Error("Technical not found"));

        if (technical.getCalls().size() > 0) {
            throw new Error("Technical have opened calls and can't bem deleted");
        }
        
        repository.deleteById(id);
    }
}
