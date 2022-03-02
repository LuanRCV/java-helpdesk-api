package com.lrcv.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lrcv.helpdesk.domain.Tecnico;
import com.lrcv.helpdesk.domain.dtos.TecnicoDTO;
import com.lrcv.helpdesk.repositories.TecnicoRepository;
import com.lrcv.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO tecnicoObjDTO) {
		tecnicoObjDTO.setId(null);
		Tecnico tecnico = new Tecnico(tecnicoObjDTO);

		return repository.save(tecnico);
	}
}
