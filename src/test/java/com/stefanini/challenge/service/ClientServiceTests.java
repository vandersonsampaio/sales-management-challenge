package com.stefanini.challenge.service;

import com.stefanini.challenge.model.Client;
import com.stefanini.challenge.model.repository.IClientRepository;
import com.stefanini.challenge.service.implementation.ClientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class ClientServiceTests {

    private ClientService service;
    private IClientRepository repository;

    @Before
    public void setUp(){
        repository = Mockito.mock(IClientRepository.class);
        service = new ClientService(repository);
    }

    @Test
    public void findAllClientsTest() {
        Client client = Client.builder().name("Client Name").build();
        List<Client> expected = Collections.singletonList(client);
        when(repository.findAll()).thenReturn(Collections.singletonList(client));

        List<Client> actual = service.findAllClients();

        Assert.assertTrue(actual.size() > 0);
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getName(), actual.get(0).getName());
    }

    @Test
    public void getByNameTest_Exists() {
        String nameClient = "Client Name";
        Client client = Client.builder().name(nameClient).build();
        when(repository.findById(nameClient)).thenReturn(Optional.of(client));

        Client actual = service.getById(nameClient);

        Assert.assertNotNull(actual);
        Assert.assertEquals(nameClient, actual.getName());
    }

    @Test
    public void getByNameTest_Null() {
        String nameClient = "Product Name";
        when(repository.findById(nameClient)).thenReturn(Optional.empty());

        Client actual = service.getById(nameClient);

        Assert.assertNull(actual);
    }
}
