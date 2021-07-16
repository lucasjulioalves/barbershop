package br.com.barbershop.adapter;

import br.com.barbershop.dto.response.otp.OtpResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface OtpApiAdapter<T> {

    T getStatusByPhoneNumber(String phoneNumber);
}
