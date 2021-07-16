package br.com.barbershop.adapter.impl;

import br.com.barbershop.adapter.OtpApiAdapter;
import br.com.barbershop.dto.response.otp.OtpResponseDTO;
import br.com.barbershop.enums.TokenTwoFactorAuthStatusEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Component
public class WebClientOtpApiAdapterImpl implements OtpApiAdapter<OtpResponseDTO> {

    @Value("${app.otp.url}")
    private String otpServerUrl;

    private WebClient webClient;

    @PostConstruct
    public void setUp() {
        webClient = WebClient.builder()
                .baseUrl(otpServerUrl)
                .build();
    }

    @Override
    public OtpResponseDTO getStatusByPhoneNumber(String phoneNumber) {

        OtpResponseDTO response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/phone/{phoneNumber}")
                        .build(phoneNumber))
                .retrieve()
                .bodyToMono(OtpResponseDTO.class)
                .block();

        return response;
    }
}
