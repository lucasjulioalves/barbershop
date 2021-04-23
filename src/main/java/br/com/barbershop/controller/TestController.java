package br.com.barbershop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PreAuthorize("hasAuthority('USER') or hasAuthority('SU') or hasAuthority('ADMIN')")
    @GetMapping("/test/user")
    public String hey() {
        return "testando...";
    }

    @PreAuthorize("hasAuthority('SU') or hasAuthority('ADMIN')")
    @GetMapping("/test/admin")
    public String hey2() {
        return "testando...";
    }
}
