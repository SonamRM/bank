package com.banking.bank.Utils;

import com.banking.bank.entity.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil {
    public static final String SECRET_KEY = "asdfasdfsdfsadfsadfw83irekwfsd333refwew3rewfewecwer3fc3f";
    private static final long EXPIRATION_TIME = 300000; //5 min time in milliseconds

    public static String generateToken(String userId){
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("user_id", userId);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME *1000)).compact();

    }
}
