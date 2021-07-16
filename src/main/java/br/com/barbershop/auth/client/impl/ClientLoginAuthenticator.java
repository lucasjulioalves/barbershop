package br.com.barbershop.auth.client.impl;

import br.com.barbershop.auth.client.ClientAuthenticator;
import br.com.barbershop.enums.TokenTwoFactorAuthStatusEnum;
import br.com.barbershop.facade.AuthResponseFacade;
import br.com.barbershop.model.Client;
import br.com.barbershop.service.ClientService;
import br.com.barbershop.service.TwoFactorAuthService;
import br.com.barbershop.service.impl.ClientServiceImpl;
import br.com.barbershop.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ClientLoginAuthenticator implements ClientAuthenticator<AuthResponseFacade>  {

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtUtils;

    private ClientService clientService;

    private TwoFactorAuthService twoFactorAuthService;

    @Autowired
    public ClientLoginAuthenticator(AuthenticationManager authenticationManager, JwtUtils jwtUtils, ClientService clientService, TwoFactorAuthService twoFactorAuthService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.clientService = clientService;
        this.twoFactorAuthService = twoFactorAuthService;
    }

    @Override
    public AuthResponseFacade authenticateUsernamePassword(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        return generateResponse(authentication, username);
    }

    private AuthResponseFacade generateResponse(Authentication authentication, String username) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        Client client = clientService.findByEmailOrPhone(username).get();

        TokenTwoFactorAuthStatusEnum tokenStatus =
                twoFactorAuthService.getTokenStatusByPhoneNumber(client.getPhoneNumber());

        if (!client.isTwoFactorAuthEnabled() || tokenStatus.equals(TokenTwoFactorAuthStatusEnum.VALIDATED)) {
            return new AuthResponseFacade(jwt);
        } else {
            return new AuthResponseFacade().withTwoFactorAuthEnabled(true);
        }

    }
}
