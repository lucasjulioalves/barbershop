package br.com.barbershop.model;

import br.com.barbershop.enums.RoleEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String phoneNumber;
    private String name;
    private String lastName;
    private String password;
    private boolean enabled;
    private boolean twoFactorAuthEnabled;

    public Client() {

    }

    public Client(
            Long id,
            String email,
            String phoneNumber,
            String name,
            String lastName,
            String password,
            boolean enabled) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.enabled = enabled;
    }

    public Client withId(Long id) {
        this.id = id;
        return this;
    }

    public Client withEmail(String email) {
        this.email = email;
        return this;
    }

    public Client withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Client withName(String name) {
        this.name = name;
        return this;
    }

    public Client withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Client withPassword(String password) {
        this.password = password;
        return this;
    }

    public Client withEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Client withTwoFactorAuthEnabled(boolean twoFactorAuthEnabled) {
        this.twoFactorAuthEnabled = twoFactorAuthEnabled;
        return this;
    }
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Roles> roles = new ArrayList<>();

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

    public List<Roles> getRoles() {
        return roles;
    }
    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public boolean isTwoFactorAuthEnabled() {
        return twoFactorAuthEnabled;
    }

    public void setTwoFactorAuthEnabled(boolean twoFactorAuthEnabled) {
        this.twoFactorAuthEnabled = twoFactorAuthEnabled;
    }

    public void addRole(RoleEnum roleEnum) {
        Roles role = new Roles();
        role.setRole(roleEnum);
        this.roles.add(role);
    }


}
