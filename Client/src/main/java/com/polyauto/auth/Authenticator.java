package com.polyauto.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.hash.Hashing;
import com.polyauto.entities.UsersEntity;
import com.polyauto.exceptions.UnauthorizedException;
import com.polyauto.utilities.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Classe Authenticator
 * Gère tous les services liés aux tokens
 */
@Service
public class Authenticator
{
    @Autowired
    private Environment env0;

    private static Environment env;


    /**
     * Initialise l'environnment dans le contexte statique
     */
    @PostConstruct
    private void initAuthenticator()
    {
        env = env0;
    }

    /**
     * @param token le token à décodé
     * @return DecodedJWT le token décodé
     * @throws RuntimeException
     */
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

            //Vérification de la chaine de vérification
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
            //Si l'authentification a échoué on renvoie une 400
            System.out.println(exception.getMessage());
            throw new UnauthorizedException();
        }

    }

    /**
     * @param login
     * @param userId
     * @param nonce Un nombre aléatoire à usage uniqye
     * @return le token
     */
    public static String generateVerificationString(String login,String userId,String nonce)
    {
        return Hashing.sha256().hashString(login+nonce+userId+nonce+env.getProperty("polyauto.verificationKey"), StandardCharsets.UTF_8).toString();
    }

    /**
     * @param user
     * @return
     */
    public static String buildToken(UsersEntity user)
    {
        Algorithm algorithm = Algorithm.HMAC256(env.getProperty("polyauto.secretKey"));

        //Le nonce va servir à créer une clé de vériication uniuque pour permettre une double authentification du jwt
        String nonce = new RandomString(64).nextString();

        String verification = generateVerificationString(user.getLogin(),String.valueOf(user.getIdUser()),nonce);

        //Ajout de tous les claims
        String token = JWT.create()
                .withClaim("user",user.getLogin())
                .withClaim("userId",String.valueOf(user.getIdUser()))
                .withClaim("isAdmin",String.valueOf(user.getAdmin()))
                .withClaim("nonce",nonce)
                .withClaim("verification",verification)
                .withIssuer("polyauto")
                .withIssuedAt(new Date())
                .sign(algorithm);

        return token;
    }
}
