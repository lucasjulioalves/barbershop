package br.com.barbershop.helper;

import br.com.barbershop.enums.RoleEnum;
import br.com.barbershop.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ClientCrudHelper {

    private PasswordEncoder passwordEncoder;

    @Autowired
    ClientCrudHelper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void prepareClientInstanceForCreation(Client client) {
        final String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        client.addRole(RoleEnum.SU);
    }

}
