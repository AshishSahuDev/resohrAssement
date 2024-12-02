package com.resohr.wedding.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resohr.wedding.dto.ClientDTO;
import com.resohr.wedding.entity.Client;
import com.resohr.wedding.exception.ClientNotFoundException;
import com.resohr.wedding.repository.ClientRepository;

@Service("clientService1")
public class ClientService implements IClientService {

	@Autowired
	private ClientRepository clientRepo;
	
	@Override
	public Client registerClient(ClientDTO clientDTO) {
		
		Client client = new Client();
		client.setAddress(clientDTO.getAddress());
		client.setBudget(clientDTO.getBudget());
		client.setName(clientDTO.getName());
		client.setPhoneNumber(clientDTO.getPhoneNumber());
		client.setRegistrationDate(LocalDate.now());
		return clientRepo.save(client);
	}

	@Override
	public Client getClientById(Long id) {
		 return clientRepo.findById(id).orElseThrow(()->new ClientNotFoundException("Client not Found"));
		
	}

	@Override
	public List<Client> getAllClient() {
		return clientRepo.findAll();
	}

}
