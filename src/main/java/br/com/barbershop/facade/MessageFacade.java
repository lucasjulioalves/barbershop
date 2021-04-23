package br.com.barbershop.facade;

public class MessageFacade {

    private String message;

    public MessageFacade(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
