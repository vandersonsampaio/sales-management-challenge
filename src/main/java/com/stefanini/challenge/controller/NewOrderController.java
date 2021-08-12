package com.stefanini.challenge.controller;

import com.stefanini.challenge.model.Client;
import com.stefanini.challenge.service.interfaces.IClientService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ManagedBean
@ViewScoped
public class NewOrderController implements Serializable {

    private final IClientService clientService;
    private List<String> informationClients;
    private String client;

    public NewOrderController(IClientService clientService){
        this.clientService = clientService;
    }

    @PostConstruct
    public void init() {
        informationClients = clientService.findAllClients().stream().map(Client::toString).collect(Collectors.toList());
    }

    public List<String> completeText(String query) {
        String queryLowerCase = query.toLowerCase();

        return informationClients.stream().filter(t -> t.toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
