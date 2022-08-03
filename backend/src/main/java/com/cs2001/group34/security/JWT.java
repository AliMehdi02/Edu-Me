package com.cs2001.group34.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWT {

    static final long EXPIRATIONTIME = 864_000_00;
    private String SIGNinKey = "Ali786";
    
    private Claims GA(String t) {
        return Jwts.parser().setSigningKey(SIGNinKey).parseClaimsJws(t).getBody();
    }

    public <X> X GC(String t, Function<Claims, X> CR) {
        final Claims c = GA(t);
        return CR.apply(c);
    }

    public String gUN(String t) {
        return GC(t, Claims::getSubject);
    }

    public Date GE(String t) {
        return GC(t, Claims::getExpiration);
    }

    private Boolean ET(String t) {
        return GE(t).before(new Date());
    }

    public String CT(String un) {
        Map<String, Object> c = new HashMap<>();
        return createToken(c, un);
    }

    private String createToken(Map<String, Object> c, String un) {

        return Jwts.builder().setSubject(un).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS256, SIGNinKey).compact();
    }

    public Boolean CT(String t, UserDetails ud) {
        final String un = gUN(t);
        return (un.equals(ud.getUsername()) && !ET(t));
    }

}
