package br.com.barbershop.facade;

import br.com.barbershop.model.Client;
import br.com.barbershop.model.Roles;

public class ClientFacade {

    private Long id;
    private String email;
    private String phoneNumber;
    private String name;
    private String lastName;
    private String password;
    private boolean enabled;


    public Client toClient() {
        return new Client(
                id,
                email,
                phoneNumber,
                name,
                lastName,
                password,
                enabled
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
