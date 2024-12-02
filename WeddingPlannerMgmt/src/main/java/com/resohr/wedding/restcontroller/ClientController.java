package com.resohr.wedding.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resohr.wedding.dto.ClientDTO;
import com.resohr.wedding.entity.Client;
import com.resohr.wedding.service.IClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private IClientService clientService;

	@PostMapping
	public Client registerClient(@Valid @RequestBody ClientDTO clientDTO) {
		return clientService.registerClient(clientDTO);
	}

	@GetMapping("/{id}")
	public Client getClientById(@PathVariable Long id) {
		return clientService.getClientById(id);
	}

	@GetMapping
	public List<Client> getAllClients() {
		return clientService.getAllClient();
	}
}
