package com.lrcv.helpdesk.modules.technical.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.lrcv.helpdesk.modules.technical.domain.models.Technical;
import com.lrcv.helpdesk.modules.technical.domain.models.dtos.TechnicalDTO;
import com.lrcv.helpdesk.modules.technical.services.CreateTechnicalService;
import com.lrcv.helpdesk.modules.technical.services.DeleteTechnicalService;
import com.lrcv.helpdesk.modules.technical.services.ListTechnicalService;
import com.lrcv.helpdesk.modules.technical.services.ShowTechnicalService;
import com.lrcv.helpdesk.modules.technical.services.UpdateTechnicalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/technicals")
public class TechnicalController {

    @Autowired
    private ListTechnicalService listTechnicalService;

    @Autowired
    private ShowTechnicalService showTechnicalService;

    @Autowired
    private CreateTechnicalService createTechnicalService;

    @Autowired
    private UpdateTechnicalService updateTechnicalService;

    @Autowired
    private DeleteTechnicalService deleteTechnicalService;

    @GetMapping
    public ResponseEntity<List<TechnicalDTO>> findAll() {
        List<Technical> technicals = listTechnicalService.execute();
        List<TechnicalDTO> technicalsDTO = technicals.stream().map(technical -> new TechnicalDTO(technical))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(technicalsDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicalDTO> findById(@PathVariable Integer id) {
        Technical technical = showTechnicalService.execute(id);

        return ResponseEntity.ok().body(new TechnicalDTO(technical));
    }

    @PostMapping
    public ResponseEntity<TechnicalDTO> create(@Valid @RequestBody TechnicalDTO technicalDTO) {
        Technical technical = createTechnicalService.execute(technicalDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(technical.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnicalDTO> update(@PathVariable Integer id,
            @Valid @RequestBody TechnicalDTO technicalDTO) {
        Technical technical = updateTechnicalService.execute(id, technicalDTO);

        return ResponseEntity.ok().body(new TechnicalDTO(technical));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TechnicalDTO> delete(@PathVariable Integer id) {
        deleteTechnicalService.execute(id);

        return ResponseEntity.noContent().build();
    }
}
