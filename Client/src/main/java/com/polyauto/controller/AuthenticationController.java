package com.polyauto.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.google.common.hash.Hashing;
import com.polyauto.auth.Authenticator;
import com.polyauto.exceptions.InternalServerErrorException;
import com.polyauto.exceptions.UnauthorizedException;
import com.polyauto.utilities.GenericResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.polyauto.entities.*;
import com.polyauto.repositories.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
public class AuthenticationController
{
    @Autowired
    private Environment env;

    @Autowired
    private UsersEntityRepository usersRepository;

    @RequestMapping(method = RequestMethod.POST,value="/login",produces="application/json")
    public GenericResponse login(@RequestParam String login, @RequestParam String password) throws RuntimeException
    {
        //
        UsersEntity user =  usersRepository.findByLogin(login);

        if(user == null)
        {
            throw new UnauthorizedException();
        }

        String receivedHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();

        if(!receivedHash.equals(user.getPassword()))
        {
            throw new UnauthorizedException();
        }

        GenericResponse response = new GenericResponse();

        try {
            Algorithm algorithm = Algorithm.HMAC256(env.getProperty("polyauto.secretKey"));

            String userKey = Hashing.sha256().hashString(login+user.getIdUser()+env.getProperty("polyauto.verificationKey"), StandardCharsets.UTF_8).toString();

            String token = JWT.create()
                    .withClaim("user",login)
                    .withClaim("userId",user.getIdUser())
                    .withClaim("verificationKey",userKey)
                    .withIssuer("polyauto")
                    .withIssuedAt(new Date())
                    .sign(algorithm);
            response.addToContent("token",token);

            Authenticator.verifyToken(token);


            return response;

        } catch (JWTCreationException exception){
            System.out.println(exception.getMessage());
            throw new InternalServerErrorException();
        }
    }
}
