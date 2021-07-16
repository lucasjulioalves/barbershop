package br.com.barbershop.controller;


import br.com.barbershop.auth.client.ClientAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.barbershop.contants.ApiConstants;
import br.com.barbershop.exception.AppBusinessException;
import br.com.barbershop.facade.ClientFacade;
import br.com.barbershop.facade.AuthResponseFacade;
import br.com.barbershop.service.ClientService;

@RestController(ApiConstants.V1.VERSION)
public class ClientController {

    private ClientService clientService;

    private ClientAuthenticator<AuthResponseFacade> clientAuthenticator;
    
    @Autowired
    public ClientController(ClientService clientService, ClientAuthenticator<AuthResponseFacade> clientAuthenticator) {
        this.clientService = clientService;
        this.clientAuthenticator = clientAuthenticator;
    }

    @PostMapping(ApiConstants.V1.CLIENT_SIGNUP)
    public ResponseEntity<?> signupWithEmail(@RequestBody ClientFacade clientFacade) throws AppBusinessException {
        clientService.create(clientFacade.toClient());
        return ResponseEntity.status(204).body(null);
    }

    @PostMapping(ApiConstants.V1.CLIENT_SIGNIN)
    public ResponseEntity<?> signinWithEmail(@RequestBody ClientFacade clientFacade) {
        AuthResponseFacade authResponseFacade =
                clientAuthenticator.authenticateUsernamePassword(clientFacade.getUsername(), clientFacade.getPassword());

        return ResponseEntity.ok(authResponseFacade);
    }
}
