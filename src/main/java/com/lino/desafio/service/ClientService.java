package com.lino.desafio.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lino.desafio.dto.ClientDTO;
import com.lino.desafio.entity.Client;
import com.lino.desafio.repository.ClientRepository;
import com.lino.desafio.service.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {

		Optional<Client> obj = clientRepository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Cliente Não encontrado!!"));

		return new ClientDTO(entity);

	}

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {

		Page<Client> list = clientRepository.findAll(pageRequest);

		// esta convertendo de entidade para DTO
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

	public void delete(Long id) {

		try {

			clientRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {

			throw new ResourceNotFoundException("Id não encontrado " + id);
		}

	}
}
