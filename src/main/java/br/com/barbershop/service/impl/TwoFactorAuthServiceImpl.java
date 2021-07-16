package br.com.barbershop.service.impl;

import br.com.barbershop.adapter.OtpApiAdapter;
import br.com.barbershop.dto.response.otp.OtpResponseDTO;
import br.com.barbershop.enums.TokenTwoFactorAuthStatusEnum;
import br.com.barbershop.service.TwoFactorAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TwoFactorAuthServiceImpl implements TwoFactorAuthService {

    private OtpApiAdapter<OtpResponseDTO> otpAdapter;

    @Autowired
    public TwoFactorAuthServiceImpl(OtpApiAdapter<OtpResponseDTO> otpAdapter) {
        this.otpAdapter = otpAdapter;
    }

    @Override
    public TokenTwoFactorAuthStatusEnum getTokenStatusByPhoneNumber(String phoneNumber) {
        return otpAdapter.getStatusByPhoneNumber(phoneNumber)
                .getStatus();
    }
}
