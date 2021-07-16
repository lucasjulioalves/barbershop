package br.com.barbershop.helper;

import br.com.barbershop.exception.AppBusinessException;
import br.com.barbershop.facade.validation.ClientValidationRulesFacade;
import br.com.barbershop.facade.validation.builder.ClientValidationRulesFacadeBuilder;
import br.com.barbershop.model.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ClientCrudValidationHelperTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldSendNullFacadeInstanceToMethod() throws AppBusinessException {
        ClientCrudValidationHelper.isValidForCreation(null);
    }

    @Test(expected = AppBusinessException.class)
    public void shouldValidateClientWithEmailAlreadyTaken() throws AppBusinessException {
        ClientCrudValidationHelper.isValidForCreation(buildValidationFacadeWithBoolean(true));
    }

    @Test(expected =  AppBusinessException.class)
    public void shouldValidateClientWithInvalidEmail() throws AppBusinessException {
        Client client = new Client()
                .withEmail("test@com.com");

        ClientCrudValidationHelper.isValidForCreation(buildValidationFacadeWithClientEnabled(client));
    }
    private ClientValidationRulesFacade buildValidationFacadeWithBoolean(boolean isEmailAlreadyTaken) {
        Client client = new Client()
                .withEnabled(true);
        return new ClientValidationRulesFacadeBuilder()
                .begin()
                .withClientInstance(client)
                .isEmailAlreadyBeingUsed(isEmailAlreadyTaken)
                .build();
    }

    private ClientValidationRulesFacade buildValidationFacadeWithClientEnabled(Client client) {
        client.withEnabled(true);
        return new ClientValidationRulesFacadeBuilder()
                .begin()
                .withClientInstance(client)
                .isEmailAlreadyBeingUsed(false)
                .build();
    }
}
