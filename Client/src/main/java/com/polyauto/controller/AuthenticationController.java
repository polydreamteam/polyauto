package com.polyauto.controller;

import com.google.common.hash.Hashing;
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
import java.util.HashMap;

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
        //Algorithm algorithmHS = Algorithm.HMAC256(env.getProperty("polyauto.secretKey"));
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

        response.addToContent("test","coucou");
        return response;
    }

}
