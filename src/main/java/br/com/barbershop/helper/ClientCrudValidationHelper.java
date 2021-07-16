package br.com.barbershop.helper;

import br.com.barbershop.auth.AuthEntryPointJwt;
import br.com.barbershop.facade.validation.ClientValidationRulesFacade;
import br.com.barbershop.util.ClientAttributesValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.barbershop.exception.AppBusinessException;
import br.com.barbershop.model.Client;
import static br.com.barbershop.util.ClientAttributesValidationUtil.isValidEmail;
import static br.com.barbershop.util.ClientAttributesValidationUtil.isValidPassword;
import static br.com.barbershop.util.ClientAttributesValidationUtil.isValidPhoneNumber;

public class ClientCrudValidationHelper {

	private static final Logger logger = LoggerFactory.getLogger(ClientCrudValidationHelper.class);

	public static void isValidForCreation(ClientValidationRulesFacade clientValidationRulesFacade) throws AppBusinessException {
		verifyIfIsOkToValidateInstance(clientValidationRulesFacade);

		final Client client = clientValidationRulesFacade.getClient();
		final boolean isEmailAlreadyBeingUsed = clientValidationRulesFacade.isEmailAlreadyBeingUsed();
		final boolean isClientEnabled = client.isEnabled();

		if(!isClientEnabled) {
			throwAppBusinessExceptionWithMessage("Client must be enabled for creation");
        }

		if(isEmailAlreadyBeingUsed) {
			throwAppBusinessExceptionWithMessage("Email already being used");
		}
		if(!isValidEmail(client)) {
			throwAppBusinessExceptionWithMessage("Invalid email");
        }

        if(!isValidPassword(client)) {
			throwAppBusinessExceptionWithMessage("Invalid password");
        }
        
        if(!isValidPhoneNumber(client)) {
			throwAppBusinessExceptionWithMessage("Invalid phone number");
        }

	}

	private static void verifyIfIsOkToValidateInstance(ClientValidationRulesFacade clientValidationRulesFacade) {
		if(clientValidationRulesFacade == null) {
			throw new IllegalArgumentException("Validation instance is null");
		}
	}
	private static void throwAppBusinessExceptionWithMessage(String exceptionMessage) throws AppBusinessException {
		logger.error(exceptionMessage);
		throw new AppBusinessException(exceptionMessage);
	}
}
