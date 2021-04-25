package br.com.barbershop.helper;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.barbershop.exception.AppBusinessException;
import br.com.barbershop.model.Client;

@Component
public class ClientHelper {

	private static final Pattern PATTERN_EMAIL = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
	private static final Pattern PATTERN_PHONE = Pattern.compile("[1-9]\\d9\\d{4}\\d{4}");
	public static boolean isValidForCreation(Client client) throws AppBusinessException {

		if(!client.isEnabled()) {
            throw new AppBusinessException("Client must be enabled for creation");
        }

		if(!validEmail(client.getEmail())) {
            throw new AppBusinessException("Invalid email");
        }

        if(!validPassword(client.getPassword())) {
            throw new AppBusinessException("Invalid password");
        }
        
        if(!validPhoneNumber(client.getPhoneNumber())) {
        	throw new AppBusinessException("Invalid phone number");
        }

        return true;
	}
	
	public static boolean validEmail(String email) {
		if(StringUtils.isEmpty(email)) {
			return false;
		}
		return PATTERN_EMAIL.matcher(email).matches();
				
	}
	
	private static boolean validPassword(String password) {
		if(StringUtils.isEmpty(password)) {
			return false;
		}
		if(password.length() < 8) {
			return false;
		}
		
		return true;
	}
	
	public static boolean validPhoneNumber(String phoneNumber) {
		if(StringUtils.isEmpty(phoneNumber)) {
			return false;
		}
		
		return PATTERN_PHONE.matcher(phoneNumber).matches();
	}
}
