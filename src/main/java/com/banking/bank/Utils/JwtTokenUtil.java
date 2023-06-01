package com.banking.bank.Utils;

import com.banking.bank.entity.UserDetails;
import com.banking.bank.repository.BankRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

import java.util.Date;
public class JwtTokenUtil {
    public static final String SECRET_KEY = "A3p8iS5wX9zR2u5x8B4kG7nJ1mD6cV0lF9oH2qK5tE8yY1aC4eS7gN3jW6rZ9vM0p";
    private static final long EXPIRATION_TIME = 30000000;


    public static String generateToken(String userId, String password) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("user_id", userId);
        claims.put("password", password);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).compact();

    }

    public static  boolean verify(String authorization, BankRepository bankRepository, int accountNo) throws Exception {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authorization).getBody();
            Integer count = bankRepository.findByAccountNoAndUserId(claims.get("user_id").toString(), accountNo);
            // If the user exists, return true
            if(count > 0){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            // Exception occurred, return false or handle it accordingly
            throw new Exception(e);
        }
    }
}