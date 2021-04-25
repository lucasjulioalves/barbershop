package br.com.barbershop.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.barbershop.model.Client;
import br.com.barbershop.model.UserDetailsImpl;
import br.com.barbershop.repository.ClientRepository;

@Service
public class UserServiceDetailsImpl implements UserDetailsService {

    private ClientRepository clientRepository;

    @Autowired
    public UserServiceDetailsImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Long id = Long.parseLong(username);
		Client client = clientRepository.findById(id)
				.orElseThrow( () -> new UsernameNotFoundException("username not found"));
        return UserDetailsImpl.build(client);
    }
}
