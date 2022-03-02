package com.lrcv.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lrcv.helpdesk.domain.Chamado;
import com.lrcv.helpdesk.domain.Cliente;
import com.lrcv.helpdesk.domain.Tecnico;
import com.lrcv.helpdesk.domain.enums.Perfil;
import com.lrcv.helpdesk.domain.enums.Prioridade;
import com.lrcv.helpdesk.domain.enums.Status;
import com.lrcv.helpdesk.repositories.ChamadoRepository;
import com.lrcv.helpdesk.repositories.ClienteRepository;
import com.lrcv.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {
		Tecnico tec1 = new Tecnico(null, "Luan Vieira", "80692150030", "luan.vieira@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Linus Torvalds", "06116981020", "torvalds@gmail.com", "123");

		Chamado cham1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1,
				cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(cham1));
	}
}