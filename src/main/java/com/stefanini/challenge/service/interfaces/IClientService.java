package com.stefanini.challenge.service.interfaces;

import com.stefanini.challenge.model.Client;

import java.util.List;

public interface IClientService {

    List<Client> findAllClients();
}
