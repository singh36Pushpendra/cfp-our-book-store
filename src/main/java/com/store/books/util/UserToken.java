package com.store.books.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

// Used to denote a component.
@Component
public class UserToken {

    private static final String TOKEN_ID_SECRET = "omkar";

    public String createToken(int userId) {
        try {
            // Setting an algorithm for first part of token.
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_ID_SECRET);

            // Creating a token.
            String tokenOfId = JWT.create()
                    .withClaim("user_id", userId)
                    .sign(algorithm);

            return tokenOfId;
        } catch (JWTCreationException | IllegalArgumentException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public int decodeToken(String tokenHaveId) {
        int userId;

        Verification verification = null;

        try {
            verification = JWT.require(Algorithm.HMAC256(TOKEN_ID_SECRET));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        JWTVerifier jwtVerifier = verification.build();

        // To decode token.
        DecodedJWT decodedJWT = jwtVerifier.verify(tokenHaveId);

        Claim claim = decodedJWT.getClaim("user_id");
        userId = claim.asInt();

        return userId;
    }
}
