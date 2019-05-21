package com.polyauto.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Authenticator
{
    @Autowired
    private Environment env0;

    private static Environment env;

    @PostConstruct
    private void initAuthenticator()
    {
        env = env0;
    }

    public static boolean verifyToken(String token)
    {
        try {
            Algorithm algorithm = Algorithm.HMAC256(env.getProperty("polyauto.secretKey"));
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("polyauto")
                    .acceptLeeway(7200)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(jwt.getClaims());
        } catch (JWTVerificationException exception){

        }

        return true;
    }
}
