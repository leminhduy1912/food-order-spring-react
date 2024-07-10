package com.foodOrder.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
@Service
public class JwtProvider {
    SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
   public String generateToken(Authentication authentication){
       Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
       String roles = populateAuthorities(authorities);
       String jwt= Jwts.builder().setIssuedAt(new Date())
               .setExpiration(new Date(new Date().getTime()+86400000))
               .claim("email", authentication.getName())
               .claim("authorities",roles)
               .signWith(secretKey)
               .compact();
       return jwt;
   }
   public String getEmailFromJwt(String jwt){
       jwt= jwt.substring(7);
       Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
       String email = String.valueOf(claims.get("email"));
       return email;
   }
   private String populateAuthorities(Collection<? extends GrantedAuthority> authorities){
       Set<String> auths = new HashSet<>();
       for (GrantedAuthority authority:authorities){
           auths.add(authority.getAuthority());
       }
       return String.join(",",auths);
   }
}
