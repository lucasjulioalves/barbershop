package br.com.barbershop.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.barbershop.enums.RoleEnum;
import br.com.barbershop.exception.AppBusinessException;
import br.com.barbershop.helper.ClientHelper;
import br.com.barbershop.model.Client;
import br.com.barbershop.repository.ClientRepository;
import br.com.barbershop.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    private PasswordEncoder passwordEncoder;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Client create(Client client) throws AppBusinessException {
    	ClientHelper.isValidForCreation(client);
        if(clientRepository.existsByEmail(client.getEmail())) {
            throw new AppBusinessException("Email already taken for use");
        }
        final String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        client.addRole(RoleEnum.SU);
        return clientRepository.save(client);
    }

	@Override
	public Optional<Client> findByPhone(String phonenumber) {
		return clientRepository.findByPhoneNumber(phonenumber);
	}
    
}
