package br.com.barbershop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.barbershop.contants.ApiConstants;
import br.com.barbershop.exception.AppBusinessException;
import br.com.barbershop.facade.ClientFacade;
import br.com.barbershop.facade.JwtFacade;
import br.com.barbershop.service.ClientService;
import br.com.barbershop.util.JwtUtils;

@RestController(ApiConstants.V1.VERSION)
public class ClientController {

    private ClientService clientService;

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtUtils;
    
    @Autowired
    public ClientController(ClientService clientService,
                            AuthenticationManager authenticationManager,
                            JwtUtils jwtUtils) {
        this.clientService = clientService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(ApiConstants.V1.CLIENT_SIGNUP)
    public ResponseEntity<?> signupWithEmail(@RequestBody ClientFacade clientFacade) throws AppBusinessException {
        clientService.create(clientFacade.toClient());
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(ApiConstants.V1.CLIENT_SIGNIN)
    public ResponseEntity<?> signinWithEmail(@RequestBody ClientFacade clientFacade) throws AppBusinessException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(clientFacade.getUsername(), clientFacade.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtFacade(jwt));
    }
}
