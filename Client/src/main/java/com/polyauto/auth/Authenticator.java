package com.polyauto.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.hash.Hashing;
import com.polyauto.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

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

    public static DecodedJWT verifyAndDecodeToken(String token) throws RuntimeException
    {
        try {
            Algorithm algorithm = Algorithm.HMAC256(env.getProperty("polyauto.secretKey"));

            //Verification jwt

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("polyauto")
                    .acceptLeeway(7200)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);

            String login = jwt.getClaim("user").asString();
            String userId = jwt.getClaim("userId").asString();
            String nonce = jwt.getClaim("nonce").asString();
            String verification = generateVerificationString(login,userId,nonce);

            if(!verification.equals(jwt.getClaim("verification").asString()))
            {
                throw new JWTVerificationException("Invalid verification string");
            }
            else
            {
                return jwt;
            }
        }
        catch (JWTVerificationException exception)
        {
            System.out.println(exception.getMessage());
            throw new UnauthorizedException();
        }

    }

    public static String generateVerificationString(String login,String userId,String nonce)
    {
        return Hashing.sha256().hashString(login+nonce+userId+nonce+env.getProperty("polyauto.verificationKey"), StandardCharsets.UTF_8).toString();
    }
}
