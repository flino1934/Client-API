package com.lino.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lino.desafio.dto.ClientDTO;
import com.lino.desafio.entity.Client;
import com.lino.desafio.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {

		Page<Client> list = clientRepository.findAll(pageRequest);
		
		//esta convertendo de entidade para DTO
		return list.map(x -> new ClientDTO(x));

	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {

		Client entity = new Client();
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		clientRepository.save(entity);

		return new ClientDTO(entity);
	}

}
