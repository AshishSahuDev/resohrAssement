package com.resohr.wedding.service;

import java.util.List;

import com.resohr.wedding.dto.ClientDTO;
import com.resohr.wedding.entity.Client;

public interface IClientService {

	public Client registerClient(ClientDTO client);
	public Client getClientById(Long id);
	public List<Client> getAllClient();
}
