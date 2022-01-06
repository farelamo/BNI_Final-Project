package com.mvp.authservice.security;

import com.mvp.authservice.model.User;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("3600000")
    private Long expiration;

    public String generateToken(Authentication authentication){
        final User user = (User) authentication.getPrincipal();

        Date now = new Date(System.currentTimeMillis());
        Date expiredDate = new Date (now.getTime() + expiration);

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());

        return Jwts.builder()
                .setId(user.getId().toString())
                .setSubject(user.getUsername())
                .setClaims(claims)
                .setIssuedAt(now) //like createdAt
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            log.error("Invalid Jwt Signature: {}", ex.getMessage());
        }catch (MalformedJwtException ex) {
            log.error("Invalid Jwt Token: {}", ex.getMessage());
        }catch(ExpiredJwtException ex){
            log.error("Expired Jwt Token: {}", ex.getMessage());
        }catch (UnsupportedJwtException ex){
            log.error("Unsupported Jwt Token: {}", ex.getMessage());
        }catch (IllegalArgumentException ex){
            log.error("Jwt claim string is empty: {}", ex.getMessage());
        }
        return false;
    }

    public String getUsername(String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }
}
