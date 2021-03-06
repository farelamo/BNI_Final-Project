package com.mvp.gateway.util;

import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;
import java.util.Date;

@Log4j2
@Component
public class JwtUtil {

//    String pengecoh = "iniadalahpengecohhandalyangsangatsulitdipecahkanhehe";
//    byte[] transform = pengecoh.getBytes(Charset.forName("UTF-8"));
//    private final Key key = new SecretKeySpec(transform, SignatureAlgorithm.HS256.getJcaName());

    @Value("${jwt.secret}")
    private String secret;

    @Value("3600000")
    private Long expiration;

    public Claims getClaims(final String token) {
        try {
            //Claims body = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return body;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return null;
    }

    public String generateToken(String id){
        Claims claims = Jwts.claims().setSubject(id);
        Long nowMillis = System.currentTimeMillis();
        Long expMillis = nowMillis + expiration;
        Date exp = new Date(expMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(nowMillis)) //like createdAt
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            //Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
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

    public String getUsername(String token) {
        //Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }

}
