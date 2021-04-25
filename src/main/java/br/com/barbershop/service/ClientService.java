package br.com.barbershop.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.barbershop.exception.AppBusinessException;
import br.com.barbershop.model.Client;

@Service
public interface ClientService {
    Client create(Client client) throws AppBusinessException;
    Optional<Client> findByPhone(String phonenumber);
}
