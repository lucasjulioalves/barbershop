package br.com.barbershop.model;

import br.com.barbershop.enums.RoleEnum;

import javax.persistence.*;

@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getRole() {
        return name;
    }

    public void setRole(RoleEnum role) {
        this.name = role;
    }
}
