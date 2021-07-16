package br.com.barbershop.facade.validation.builder;

import br.com.barbershop.facade.validation.ClientValidationRulesFacade;
import br.com.barbershop.model.Client;

public class ClientValidationRulesFacadeBuilder {

    public ClientValidationRulesFacadeBuilder() {

    }
    public  Builder begin() {
        return new Builder();
    }

    public class Builder {
        ClientValidationRulesFacade clientValidationRulesFacade = new ClientValidationRulesFacade();
        public Builder withClientInstance(Client clientInstance) {
            if(clientInstance == null) {
                throw new IllegalArgumentException("Client instance needed");
            }
            clientValidationRulesFacade.setClient(clientInstance);
            return this;
        }

        public Builder isEmailAlreadyBeingUsed(boolean isEmailAlreadyBeingUsed) {
            clientValidationRulesFacade.setEmailAlreadyBeingUsed(isEmailAlreadyBeingUsed);
            return this;
        }

        public ClientValidationRulesFacade build() {
            return clientValidationRulesFacade;
        }
    }


}
