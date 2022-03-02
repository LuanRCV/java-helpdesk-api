package com.lrcv.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lrcv.helpdesk.domain.Pessoa;
import com.lrcv.helpdesk.domain.Tecnico;
import com.lrcv.helpdesk.domain.dtos.TecnicoDTO;
import com.lrcv.helpdesk.repositories.PessoaRepository;
import com.lrcv.helpdesk.repositories.TecnicoRepository;
import com.lrcv.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.lrcv.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO tecnicoObjDTO) {
		tecnicoObjDTO.setId(null);

		validaPorCpfEEmail(tecnicoObjDTO);

		Tecnico tecnico = new Tecnico(tecnicoObjDTO);

		return repository.save(tecnico);
	}

	private void validaPorCpfEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());

		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}

		obj = pessoaRepository.findByEmail(objDTO.getEmail());

		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
		}
	}
}
