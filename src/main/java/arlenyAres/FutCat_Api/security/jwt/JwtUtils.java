package arlenyAres.FutCat_Api.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private final String SECRET_KEY = "MiSecretoMuySeguro";  // Usa un valor más robusto
    private final long EXPIRATION = 86400000; // 1 día en ms

    /**
     * Genera el token JWT a partir de un username.
     */
    public String generarToken(String username) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * Obtiene el username ("sub") del token.
     */
    public String obtenerUsernameDeToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Valida firma y expiración del token.
     */
    public boolean validarToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            // Token expirado, firma inválida, etc.
            return false;
        }
    }
}
