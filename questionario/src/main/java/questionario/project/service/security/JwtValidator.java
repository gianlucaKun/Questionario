package questionario.project.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import questionario.project.service.security.util.JwtConstant;

import javax.crypto.SecretKey;

@Component
public class JwtValidator {
    
    private final SecretKey key;

    @Autowired
    public JwtValidator() {
        this.key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    }
    
    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            // Puoi eseguire ulteriori controlli qui, se necessario
            return true; // Se il token è valido
        } catch (Exception e) {
            // Gestisci l'eccezione qui, se necessario
            return false; // Se il token non è valido
        }
    }
}
