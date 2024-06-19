package com.infokey.backend.Token;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    /**
     * create a jwt token with expiration of 1 hour
     * setting the subject claim of userId
     * @param userId user id
     * @return jwt token
     */
    public String generateToken(String userId) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                                            .issuer("self")
                                            .issuedAt(now)
                                            .expiresAt(now.plus(1, ChronoUnit.HOURS))
                                            .subject(userId)
                                            .claim("scope", "USER")
                                            .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
