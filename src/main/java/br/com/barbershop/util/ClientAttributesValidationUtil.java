package br.com.barbershop.util;

import br.com.barbershop.model.Client;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class ClientAttributesValidationUtil {

    private static final Pattern PATTERN_EMAIL = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private static final Pattern PATTERN_PHONE = Pattern.compile("[1-9]\\d9\\d{4}\\d{4}");

    public static boolean isValidEmail(Client client) {
        final String email = client.getEmail();
        if(StringUtils.isEmpty(email)) {
            return false;
        }
        return PATTERN_EMAIL.matcher(email).matches();

    }

    public static boolean isValidPassword(Client client) {
        final String password = client.getPassword();
        if(StringUtils.isEmpty(password)) {
            return false;
        }
        if(password.length() < 8) {
            return false;
        }

        return true;
    }

    public static boolean isValidPhoneNumber(Client client) {
        final String phoneNumber = client.getPhoneNumber();
        if(StringUtils.isEmpty(phoneNumber)) {
            return false;
        }

        return PATTERN_PHONE.matcher(phoneNumber).matches();
    }
}
