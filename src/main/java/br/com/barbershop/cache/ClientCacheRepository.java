package br.com.barbershop.cache;

import br.com.barbershop.model.Client;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ClientCacheRepository {
    void save(Client client);
    Optional<Client> findById(Long id);
}
