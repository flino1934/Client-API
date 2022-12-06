package com.lino.desafio.resouce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lino.desafio.repository.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClienteResource {

	@Autowired
	private ClientRepository clientRepository;
	
}
