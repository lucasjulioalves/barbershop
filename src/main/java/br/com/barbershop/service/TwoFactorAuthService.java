package br.com.barbershop.service;

import br.com.barbershop.enums.TokenTwoFactorAuthStatusEnum;
import org.springframework.stereotype.Service;

@Service
public interface TwoFactorAuthService {
    TokenTwoFactorAuthStatusEnum getTokenStatusByPhoneNumber(String phoneNumber);
}
