package br.com.barbershop.auth;

import br.com.barbershop.cache.ClientCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.barbershop.model.Client;
import br.com.barbershop.model.UserDetailsImpl;
import br.com.barbershop.repository.ClientRepository;

import java.util.Optional;

@Service
public class UserServiceDetailsImpl implements UserDetailsService {

    private ClientRepository clientRepository;

    private ClientCacheRepository clientCacheRepository;

    
    @Autowired
    public UserServiceDetailsImpl(ClientRepository clientRepository, ClientCacheRepository clientCacheRepository) {
        this.clientRepository = clientRepository;
        this.clientCacheRepository = clientCacheRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Long id = Long.parseLong(username);
    	Optional<Client> cachedClient = clientCacheRepository.findById(id);
    	Client client = null;

        if(cachedClient.isEmpty()) {
            client = clientRepository.findById(id)
                    .orElseThrow( () -> new UsernameNotFoundException("username not found"));

            clientCacheRepository.save(client);
        } else {
            client = cachedClient.get();
        }


        return UserDetailsImpl.build(client);
    }
}
