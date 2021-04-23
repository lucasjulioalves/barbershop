package br.com.barbershop.service.impl;

import br.com.barbershop.enums.RoleEnum;
import br.com.barbershop.exception.AppBusinessException;
import br.com.barbershop.model.Client;
import br.com.barbershop.repository.ClientRepository;
import br.com.barbershop.service.ClientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public Client createWithEmail(Client client) throws AppBusinessException {
        if(StringUtils.isEmpty(client.getEmail())) {
            throw new AppBusinessException("Invalid email");
        }

        if(StringUtils.isEmpty(client.getPassword())) {
            throw new AppBusinessException("Invalid password");
        }

        if(!client.isEnabled()) {
            throw new AppBusinessException("Client must be enabled for creation");
        }

        if(clientRepository.existsByEmail(client.getEmail())) {
            throw new AppBusinessException("Email already taken for use");
        }
        final String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        client.addRole(RoleEnum.USER);
        return clientRepository.save(client);
    }
}
