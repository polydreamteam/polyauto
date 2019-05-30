package com.polyauto.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.polyauto.auth.Authenticator;
import com.polyauto.entities.BookingsEntity;
import com.polyauto.entities.UsersEntity;
import com.polyauto.repositories.BookingsEntityRepository;
import com.polyauto.repositories.UsersEntityRepository;
import com.polyauto.utilities.DbManager;
import com.polyauto.utilities.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ProfileController
{
    @Autowired
    private UsersEntityRepository usersRepository;

    @Autowired
    private BookingsEntityRepository bookingsRepository;

    @RequestMapping(method = RequestMethod.GET,value="/getProfileComplete",produces="application/json")
    public GenericResponse getProfileComplete(@RequestParam String token) throws RuntimeException
    {
        DecodedJWT jwt_decoded = Authenticator.verifyAndDecodeToken(token);
        int userId = Integer.parseInt(jwt_decoded.getClaim("userId").asString());

        UsersEntity user = usersRepository.findByIdUser(userId);

        List<BookingsEntity> bookings = new ArrayList<>();//bookingsRepository.findAllByIdUser(userId);

        GenericResponse response = new GenericResponse();
        response.addToContent("user",user);
        response.addToContent("bookings",bookings);
        return response;
    }

    @RequestMapping(method = RequestMethod.GET,value="/getProfileSimple",produces="application/json")
    public GenericResponse getProfileSimple(@RequestParam String token) throws RuntimeException
    {
        DecodedJWT jwt_decoded = Authenticator.verifyAndDecodeToken(token);
        int userId = Integer.parseInt(jwt_decoded.getClaim("userId").asString());

        UsersEntity user = usersRepository.findByIdUser(userId);

        GenericResponse response = new GenericResponse();

        response.addToContent("user",user);
        return response;
    }

    @RequestMapping(method = RequestMethod.POST,value="/modifyProfileInfo",produces="application/json")
    public GenericResponse modifyProfileInfo(@RequestParam String token,
                                             String login,
                                             String password,
                                             String firstname,
                                             String lastname,
                                             Integer note
                                            ) throws Exception
    {
        //Verify token
        DecodedJWT jwt_decoded = Authenticator.verifyAndDecodeToken(token);

        //Get id from token
        int userId = Integer.parseInt(jwt_decoded.getClaim("userId").asString());

        //Find user by id
        UsersEntity user = usersRepository.findByIdUser(userId);

        //Update user with optional parameters
        if (login != null)
            user.setLogin(login);
        if (password != null)
            user.setPassword(password);
        if (firstname != null)
            user.setFirstname(firstname);
        if (lastname != null)
            user.setLastname(lastname);
        if (note != null)
            user.setNote(note);

        //TODO : Fix la sauvegarde en DB
        DbManager.saveProfile(user);

        //send back the updated user
        GenericResponse response = new GenericResponse();
        response.addToContent("user",user);

        return response;
    }
}
