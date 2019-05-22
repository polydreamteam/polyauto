package com.polyauto.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.polyauto.auth.Authenticator;
import com.polyauto.entities.BookingsEntity;
import com.polyauto.entities.UsersEntity;
import com.polyauto.repositories.BookingsEntityRepository;
import com.polyauto.repositories.UsersEntityRepository;
import com.polyauto.utilities.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
}
