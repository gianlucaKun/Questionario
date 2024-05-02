package questionario.project.controller;

import jakarta.servlet.http.HttpServletResponse;
import questionario.project.configuration.WebSecurityConfiguration;
import questionario.project.dto.AuthenticationResponse;
import questionario.project.dto.UtenteDTO;
import questionario.project.entita.Utente;
import questionario.project.repository.UtenteRepository;
import questionario.project.service.UserDetailsServiceImpl;
import questionario.project.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    private UtenteRepository ur;
    

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody UtenteDTO utenteDTO, HttpServletResponse response) throws IOException {
        try {
            // Effettua l'autenticazione
            boolean isAuthenticated = authenticate(utenteDTO.getUsername(), utenteDTO.getPassword());

            if (!isAuthenticated) {
                throw new BadCredentialsException("Credenziali errate");
            }

            // Ottieni i dettagli dell'utente
            final UserDetails userDetails = userDetailsService.loadUserByUsername(utenteDTO.getUsername());

            // Genera il token JWT utilizzando la chiave generata dal JwtUtil
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());

            // Restituisci il token JWT nella risposta
            return new AuthenticationResponse(jwt);
        } catch (BadCredentialsException e) {
            // Gestisci le eccezioni
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Autenticazione fallita");
            return null;
        }
    }

    private boolean authenticate(String username, String password) {
        Utente utente = ur.findByUsername(username);
        if (utente != null) {

            return passwordEncoder.matches(password, utente.getPassword());
        }
        return false;
    }

}