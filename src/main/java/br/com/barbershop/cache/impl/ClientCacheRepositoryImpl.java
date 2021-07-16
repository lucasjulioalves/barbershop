package br.com.barbershop.cache.impl;

import br.com.barbershop.cache.ClientCacheRepository;
import br.com.barbershop.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientCacheRepositoryImpl implements ClientCacheRepository {

    private RedisTemplate<String, Client> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public ClientCacheRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Client client) {
        hashOperations.put("CLIENT", client.getId(), client);
    }

    @Override
    public Optional<Client> findById(Long id) {
        Optional<Client> client = Optional.of(
                (Client) hashOperations.get("CLIENT", id));

        return client;
    }
}
