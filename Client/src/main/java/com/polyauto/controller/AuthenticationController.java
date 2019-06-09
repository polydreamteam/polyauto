package com.polyauto.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.google.common.hash.Hashing;

import com.polyauto.auth.Authenticator;
import com.polyauto.exceptions.InternalServerErrorException;
import com.polyauto.exceptions.UnauthorizedException;
import com.polyauto.utilities.GenericResponse;

import com.polyauto.utilities.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import com.polyauto.entities.*;
import com.polyauto.repositories.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
public class AuthenticationController
{
    @Autowired
    private UsersEntityRepository usersRepository;

    @CrossOrigin(origins = "http://localhost:4200")
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

            String token = Authenticator.buildToken(user);

            response.addToContent("token",token);
            response.addToContent("isAdmin",String.valueOf(user.getAdmin()));
            response.addToContent("userId",String.valueOf(user.getIdUser()));
            return response;

        } catch (JWTCreationException exception){
            System.out.println(exception.getMessage());
            throw new InternalServerErrorException();
        }
    }
}
