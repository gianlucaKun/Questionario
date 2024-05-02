package questionario.project.service;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//public class JwtService {
//
//    private static final String SECRET_KEY ="wpembytrwcvnryxksdbqwjebruyGHyudqgwveytrtrCSnwifoesarjbwe"; // Chiave segreta utilizzata per firmare il token
//
//    public static String generateJwtToken(Long userId) {
//        // Creazione del token JWT includendo l'id dell'utente come claim
//        String jwtToken = Jwts.builder()
//                .setSubject(String.valueOf(userId)) // Imposta il subject (claim) come l'id dell'utente
//                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // Firma il token con la chiave segreta
//                .compact();    
//        return jwtToken;
//    }
//
//    public static Long getUserIdFromJwtToken(String jwt) {
//        // Parsing del token JWT e ottenimento dei claims
//        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
//        
//        // Estrai l'id dell'utente dal claim
//        Long userId = Long.parseLong(claims.getSubject());
//        
//        return userId;
//    }
//}
