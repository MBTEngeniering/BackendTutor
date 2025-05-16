package de.fairstudy.backend.service.api;

import de.fairstudy.backend.dto.AllUserDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${app.jwtSecret}")
    private String SECRET_KEY;

    @Value("${app.jwtExpirationMs}")
    private long TTL;

    public String extractUsername(String token) throws ExpiredJwtException {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws ExpiredJwtException, WeakKeyException {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(AllUserDto userDetails) {
        // Generate token with empty extra claims
        var hm = new HashMap<String, Object>();
        hm.put("userRoleIds", userDetails.getUserRoleIds().toString());
        hm.put("userId", userDetails.getId());
        return generateToken(hm, userDetails);
    }

    public String expirationTimeAsString(String token) {
        return extractClaim(token, Claims::getExpiration).toString();
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            AllUserDto userDetails
    ) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getEmailAddress())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TTL))
                .signWith(getSignInKey(), Jwts.SIG.HS512)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token).getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            // Token is invalid
            throw e;
        }

        return true;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username;
        try {
            // First check
            Jwts.parser().verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token).getPayload();

            // Second check
            username = extractUsername(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }

        // 3rd check - Check if the token is valid and not expired
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) throws ExpiredJwtException, SignatureException {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Create HttpOnly cookie
    public ResponseCookie generateJwtCookie(String token) {
        return ResponseCookie.from("jwt", token)
                .httpOnly(true) // Prevent XSS
                .secure(false)   // Send only over HTTPS
                .path("/")      // Accessible across all paths
                .maxAge(TTL / 1000) // Cookie expiration (seconds)
                .sameSite("Lax") // CSRF protection
                .build();
    }
}
