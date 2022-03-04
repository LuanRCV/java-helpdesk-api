package com.lrcv.helpdesk.modules.call.services;

import java.util.Optional;

import com.lrcv.helpdesk.modules.call.domain.models.Call;
import com.lrcv.helpdesk.modules.call.domain.repositories.CallRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowCallService {

    @Autowired
    private CallRepository repository;

    public Call execute(Integer id) {
        Optional<Call> call = repository.findById(id);

        return call.orElseThrow(() -> new Error("Call not found, Id:" + id));
    }
}
