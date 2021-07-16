package br.com.barbershop.service.impl;

import java.util.Optional;

import br.com.barbershop.facade.validation.ClientValidationRulesFacade;
import br.com.barbershop.facade.validation.builder.ClientValidationRulesFacadeBuilder;
import br.com.barbershop.helper.ClientCrudHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.barbershop.exception.AppBusinessException;
import br.com.barbershop.helper.ClientCrudValidationHelper;
import br.com.barbershop.model.Client;
import br.com.barbershop.repository.ClientRepository;
import br.com.barbershop.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    private ClientCrudHelper clientCrudHelper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientCrudHelper clientCrudHelper) {
        this.clientRepository = clientRepository;
        this.clientCrudHelper = clientCrudHelper;
    }

    @Override
    public Client create(Client client) throws AppBusinessException {
        final ClientValidationRulesFacade clientValidationRulesFacade = new ClientValidationRulesFacadeBuilder()
                        .begin()
                        .withClientInstance(client)
                        .isEmailAlreadyBeingUsed(clientRepository.existsByEmail(client.getEmail()))
                        .build();

    	ClientCrudValidationHelper.isValidForCreation(clientValidationRulesFacade);
        clientCrudHelper.prepareClientInstanceForCreation(client);

        return clientRepository.save(client);
    }

	@Override
	public Optional<Client> findByPhone(String phonenumber) {
		return clientRepository.findByPhoneNumber(phonenumber);
	}

    @Override
    public boolean isTwoFactorAuthEnabled(String username) {

        return findByEmailOrPhone(username)
                .get()
                .isTwoFactorAuthEnabled();
    }

    @Override
    public Optional<Client> findByEmailOrPhone(String username) {

        Optional<Client> client;
        client = clientRepository.findByEmail(username);

        if(client.isEmpty()) {
            client = clientRepository.findByPhoneNumber(username);
        }

        return client;
    }
}
