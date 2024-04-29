package questionario.project.service.security;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import questionario.project.repository.UtenteRepository;
import questionario.project.service.security.util.JwtConstant;

import javax.crypto.SecretKey;


import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    @Value("${jwt.expiration}")
//    private long expirationTime;
	
	@Autowired
	UtenteRepository ur;
	
	

	private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
	public String generateToken(Authentication auth) {
	    long currentTimeMillis = System.currentTimeMillis();
	    long expirationTimeMillis = currentTimeMillis + 86400000; // Aggiungi 24 ore in millisecondi
	    
	    Date issuedAt = new Date(currentTimeMillis);
	    Date expiration = new Date(expirationTimeMillis);
	    
	    String jwt = Jwts.builder()
	            .setIssuedAt(issuedAt)
	            .setExpiration(expiration)
	            .claim("email", auth.getName())
	            .signWith(key)
	            .compact();
	    return jwt;
	}
	
	public String getUsernameFromJwtToken(String jwt) {
		jwt=jwt.substring(7);
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		String username = String.valueOf(claims.get("username"));
		return username;
	}
	
	public String populateAuthorities(Collection <? extends GrantedAuthority> collection) {
		Set<String> auths = new HashSet<>();
		for(GrantedAuthority authority:collection) {
			auths.add(authority.getAuthority());
		}
		
		return String.join(",", auths);
	}
	
	
}



//public String generateToken(String username) {
//Utente u = ur.findByUsername(username);
//Claims c = Jwts.claims().setSubject(u.getUsername());
//
//// Calcola la data di scadenza come 'now + expirationTime'
//Date now = new Date(System.currentTimeMillis());
//Date expirationDate = new Date(now.getTime() + expirationTime);
//
//return Jwts.builder()
//        .setClaims(c)
//        .setIssuedAt(now)
//        .setExpiration(expirationDate)
//        .signWith(SignatureAlgorithm.HS256, secretKey)
//        .compact();
//}
//
//public Claims parseToken(String token) {
//return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//}
//