package br.com.barbershop.auth.client;

import org.springframework.stereotype.Service;

@Service
public interface ClientAuthenticator<T> {

    T authenticateUsernamePassword(String username, String password);
}
