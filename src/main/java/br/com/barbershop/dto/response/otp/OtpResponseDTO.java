package br.com.barbershop.dto.response.otp;

import br.com.barbershop.enums.TokenTwoFactorAuthStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OtpResponseDTO {

    private TokenTwoFactorAuthStatusEnum status;

    public TokenTwoFactorAuthStatusEnum getStatus() {
        return status;
    }
}
