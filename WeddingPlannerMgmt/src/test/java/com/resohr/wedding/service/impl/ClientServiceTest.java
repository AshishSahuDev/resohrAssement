package com.resohr.wedding.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.resohr.wedding.dto.ClientDTO;
import com.resohr.wedding.entity.Client;
import com.resohr.wedding.repository.ClientRepository;
import com.resohr.wedding.service.ClientService;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerClient_ShouldReturnRegisteredClient() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John Doe");
        clientDTO.setPhoneNumber("1234567890");
        clientDTO.setAddress("123 Main Street");
        clientDTO.setBudget(5000.0);

        Client client = new Client();
        client.setClientId(1L);
        client.setName(clientDTO.getName());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        client.setAddress(clientDTO.getAddress());
        client.setBudget(clientDTO.getBudget());
        client.setRegistrationDate(LocalDate.now());

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client result = clientService.registerClient(clientDTO);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void getClientById_ShouldReturnClient_WhenClientExists() {
        Client client = new Client();
        client.setClientId(1L);
        client.setName("John Doe");
        client.setRegistrationDate(LocalDate.now());
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client result = clientService.getClientById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getClientId());
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void getClientById_ShouldReturnNull_WhenClientDoesNotExist() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        Client result = clientService.getClientById(1L);

        assertNull(result);
        verify(clientRepository, times(1)).findById(1L);
    }
}
