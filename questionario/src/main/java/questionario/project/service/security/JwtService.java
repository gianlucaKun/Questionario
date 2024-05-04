package questionario.project.service.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import questionario.project.dto.UtenteDTO;
import questionario.project.entita.Utente;
import questionario.project.repository.UtenteRepository;
import questionario.project.service.UtenteService;

@Service
public class JwtService {
	
//    @Value("${jwt.secret}")
//    private String SECRET_KEY;
//
//    @Value("${jwt.expiration}")
//    private long EXPIRATION_TIME;
	
    // Chiave segreta per la firma del token
    private static final String SECRET_KEY = "afsergthrjydjyh53g43t34gt5g5hhtrhtrhyrjtmutdmmuymygrnryhrhrthyhbegrgfgdgergetg55y64225y46u7i7";

    // Durata di validità del token in millisecondi (1 ora)
    private static final long EXPIRATION_TIME = 3600000;
    
    @Autowired
	private UtenteRepository ur;
    
    // Metodo per generare un nuovo token JWT
    public String generateToken(String username) {
    	System.out.println(username);
    	Utente u = ur.findByUsername(username);
    	//System.out.println(u.getPassword());
    	
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .claim("id", u.getId())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY )
                .compact();
    }

    // Metodo per verificare la validità di un token JWT
    public boolean validateToken(String token) {
        try {
            // Effettua il parsing del token JWT e verifica la firma utilizzando la chiave segreta
            Claims claims = parseToken(token);
            System.out.println("sei nel validate token e stai prendendo l'id dell'utente");
            System.out.println(claims.get("id"));
            // Se il parsing è avvenuto con successo, il token è valido
            return true;
        } catch (Exception e) {
            // Se si verifica un'eccezione durante il parsing del token, il token non è valido
            return false;
        }
    }
    
    //prende un toke e ne restituisce un claim
    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
	
}
