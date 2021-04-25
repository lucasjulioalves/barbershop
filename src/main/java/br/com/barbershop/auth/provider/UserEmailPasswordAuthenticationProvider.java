package br.com.barbershop.auth.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.barbershop.model.Client;
import br.com.barbershop.repository.ClientRepository;

@Component
public class UserEmailPasswordAuthenticationProvider implements AuthenticationProvider {

	private ClientRepository clientRepository;
	
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
	@Autowired
	public UserEmailPasswordAuthenticationProvider(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String email = authentication.getName();
		final String password = authentication.getCredentials().toString();
		final Client client = clientRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("username not found"));
	
		final String clientPassword = client.getPassword();
		
		if(passwordEncoder.matches(password, clientPassword)) {
			return new UsernamePasswordAuthenticationToken(client.getId(), clientPassword);
		}
		throw new BadCredentialsException("Wrong credentials");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
