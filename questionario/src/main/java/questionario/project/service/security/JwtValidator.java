package questionario.project.service.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
	
    @Value("${jwt.secret}")
    private String secretKey;
    
    public boolean validateToken(String token) {
        try {
            // Effettua il parsing del token JWT e verifica la firma utilizzando la chiave segreta
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            System.out.println(claims.get("roles"));
            System.out.println(claims.get("nome"));
            Date expirationDate = claims.getExpiration();
            // Se il parsing è avvenuto con successo, il token è valido
            return expirationDate.after(new Date());
        } catch (Exception e) {
            // Se si verifica un'eccezione durante il parsing del token, il token non è valido
            return false;
        }
    }
    
}
