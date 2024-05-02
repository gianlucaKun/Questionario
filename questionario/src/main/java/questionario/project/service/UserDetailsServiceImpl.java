package questionario.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import questionario.project.entita.Utente;
import questionario.project.repository.UtenteRepository;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UtenteRepository ur;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utente user = ur.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found",null);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
