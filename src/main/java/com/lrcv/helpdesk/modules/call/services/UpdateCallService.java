package com.lrcv.helpdesk.modules.call.services;

import com.lrcv.helpdesk.modules.call.domain.models.Call;
import com.lrcv.helpdesk.modules.call.domain.models.dtos.CallDTO;
import com.lrcv.helpdesk.modules.call.domain.repositories.CallRepository;
import com.lrcv.helpdesk.modules.customer.domain.models.Customer;
import com.lrcv.helpdesk.modules.customer.domain.repositories.CustomerRepository;
import com.lrcv.helpdesk.modules.technical.domain.models.Technical;
import com.lrcv.helpdesk.modules.technical.domain.repositories.TechnicalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCallService {

    @Autowired
    private CallRepository repository;

    @Autowired
    private TechnicalRepository technicalRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Call execute(CallDTO callDTO) {
        Call call = repository.findById(callDTO.getId())
                .orElseThrow(() -> new Error("Call not found. Id:" + callDTO.getId()));

        Technical technical = technicalRepository.findById(callDTO.getTechnical())
                .orElseThrow(() -> new Error("Technical not found. Id:" + callDTO.getTechnical()));

        Customer customer = customerRepository.findById(callDTO.getCustomer())
                .orElseThrow(() -> new Error("Customer not found. Id:" + callDTO.getCustomer()));

        call = new Call(callDTO, technical, customer);

        return repository.save(call);
    }
}
