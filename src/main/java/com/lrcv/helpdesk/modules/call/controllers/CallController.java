package com.lrcv.helpdesk.modules.call.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.lrcv.helpdesk.modules.call.domain.models.Call;
import com.lrcv.helpdesk.modules.call.domain.models.dtos.CallDTO;
import com.lrcv.helpdesk.modules.call.services.CreateCallService;
import com.lrcv.helpdesk.modules.call.services.ListCallService;
import com.lrcv.helpdesk.modules.call.services.ShowCallService;
import com.lrcv.helpdesk.modules.call.services.UpdateCallService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/calls")
public class CallController {

    @Autowired
    private ListCallService listCallService;

    @Autowired
    private ShowCallService showCallService;

    @Autowired
    private CreateCallService createCallService;

    @Autowired
    private UpdateCallService updateCallService;

    @GetMapping
    public ResponseEntity<List<CallDTO>> findAll() {
        List<Call> calls = listCallService.execute();
        List<CallDTO> callsDTO = calls.stream().map(call -> new CallDTO(call))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(callsDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CallDTO> findById(@PathVariable Integer id) {
        Call call = showCallService.execute(id);

        return ResponseEntity.ok().body(new CallDTO(call));
    }

    @PostMapping
    public ResponseEntity<CallDTO> create(@Valid @RequestBody CallDTO callDTO) {
        Call call = createCallService.execute(callDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(call.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CallDTO> update(@PathVariable Integer id, @Valid @RequestBody CallDTO callDTO) {
        Call call = updateCallService.execute(callDTO);

        return ResponseEntity.ok().body(new CallDTO(call));
    }
}
