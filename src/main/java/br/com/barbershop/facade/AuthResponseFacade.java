package br.com.barbershop.facade;

public class AuthResponseFacade {

    private String token;
    private boolean twoFactorAuthEnabled;

    public AuthResponseFacade() {
    }

    public AuthResponseFacade(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public boolean isTwoFactorAuthEnabled() {
        return twoFactorAuthEnabled;
    }

    public AuthResponseFacade withTwoFactorAuthEnabled(boolean twoFactorAuthEnabled) {
        this.twoFactorAuthEnabled = twoFactorAuthEnabled;
        return this;
    }
}
