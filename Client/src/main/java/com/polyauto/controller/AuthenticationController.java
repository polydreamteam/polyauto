package com.polyauto.controller;

import com.auth0.jwt.algorithms.Algorithm;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.polyauto.exceptions.BadRequestException;

import com.polyauto.entities.*;
import com.polyauto.repositories.*;

import java.util.List;

@RestController
public class AuthenticationController
{
    @Autowired
    private Environment env;

    @Autowired
    private UsersEntityRepository usersRepository;

    @RequestMapping(method = RequestMethod.POST,value="/login")
    public UsersEntity login() throws RuntimeException
    {
        Algorithm algorithmHS = Algorithm.HMAC256(env.getProperty("polyauto.secretKey"));
        return usersRepository.findByLogin("admin");
    }

}
