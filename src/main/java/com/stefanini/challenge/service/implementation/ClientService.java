package com.stefanini.challenge.service.implementation;

import com.stefanini.challenge.model.Client;
import com.stefanini.challenge.model.repository.IClientRepository;
import com.stefanini.challenge.service.interfaces.IClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService {

    private final IClientRepository repository;

    public ClientService(IClientRepository repository){
        this.repository = repository;
    }

    public List<Client> findAllClients(){
        return repository.findAll();
    }
}
