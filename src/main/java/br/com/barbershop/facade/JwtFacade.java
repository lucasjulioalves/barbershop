package br.com.barbershop.facade;

public class JwtFacade {

    private final String token;
    public JwtFacade(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
