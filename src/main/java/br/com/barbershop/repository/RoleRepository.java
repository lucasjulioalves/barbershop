package br.com.barbershop.repository;

import br.com.barbershop.enums.RoleEnum;
import br.com.barbershop.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(RoleEnum roleEnum);
}
