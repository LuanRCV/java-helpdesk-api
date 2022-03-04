package com.lrcv.helpdesk.modules.call.services;

import java.util.List;

import com.lrcv.helpdesk.modules.call.domain.models.Call;
import com.lrcv.helpdesk.modules.call.domain.repositories.CallRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListCallService {
    
    @Autowired
    private CallRepository repository;

    public List<Call> execute() {
        return repository.findAll();
    }
}
