package questionario.project.service.security.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import questionario.project.entita.Utente;
import questionario.project.repository.UtenteRepository;


@Service
public class CustomeUserServiceImplementation implements UserDetailsService {
	
	@Autowired
	private UtenteRepository ur;
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("CustomeUserServiceImplementation.loadUserByUsername()"+username);
		Optional<Utente> utente = ur.findByUsername(username);
		System.out.println(utente.get()+"User entity");
		if(utente.isEmpty()) {
			
			throw new UsernameNotFoundException("user not found with email  - "+username);
		}
		System.out.println("CustomeUserServiceImplementation.loadUserByUsername()"+ utente);
		
		List<GrantedAuthority> authorities=new ArrayList<>();
		
		
		return new org.springframework.security.core.userdetails.User(utente.get().getUsername(),utente.get().getPassword(),authorities);
	}

}
