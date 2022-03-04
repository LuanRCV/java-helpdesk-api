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
public class CreateCallService {

    @Autowired
    private CallRepository repository;

    @Autowired
    private TechnicalRepository technicalRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Call execute(CallDTO callDTO) {
        callDTO.setId(null);

        Technical technical = technicalRepository.findById(callDTO.getTechnical())
                .orElseThrow(() -> new Error("Technical not found. Id:" + callDTO.getTechnical()));

        Customer customer = customerRepository.findById(callDTO.getCustomer())
                .orElseThrow(() -> new Error("Customer not found. Id:" + callDTO.getCustomer()));

        Call call = new Call(callDTO, technical, customer);

        return repository.save(call);
    }
}
