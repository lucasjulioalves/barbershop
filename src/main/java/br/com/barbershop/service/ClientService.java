package br.com.barbershop.service;

import br.com.barbershop.exception.AppBusinessException;
import br.com.barbershop.model.Client;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
    Client createWithEmail(Client client) throws AppBusinessException;
}
