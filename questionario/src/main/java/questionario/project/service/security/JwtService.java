package questionario.project.service.security;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import questionario.project.entita.Utente;
import questionario.project.repository.UtenteRepository;

@Service
public class JwtService {
	
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;
	
	@Autowired
	UtenteRepository ur;
	
	public String generateToken(String username) {
		
		Utente u = ur.findByUsername(username);
		
		Claims c = Jwts.claims().setSubject(u.getUsername());
		//Aggiungi ruolo quando vuoi
		
        Date now = new Date(expirationTime);
		Date expirationDate = new Date(now.getTime() + expirationTime);
		
		return Jwts.builder()
                .setClaims(c)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
	}
	
	public Claims parseToken(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
	
	
	
}
