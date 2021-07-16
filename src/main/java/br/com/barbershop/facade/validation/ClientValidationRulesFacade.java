package br.com.barbershop.facade.validation;

import br.com.barbershop.model.Client;

public class ClientValidationRulesFacade {
    private Client client;
    private boolean isEmailAlreadyBeingUsed;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isEmailAlreadyBeingUsed() {
        return isEmailAlreadyBeingUsed;
    }

    public void setEmailAlreadyBeingUsed(boolean emailAlreadyBeingUsed) {
        isEmailAlreadyBeingUsed = emailAlreadyBeingUsed;
    }
}
