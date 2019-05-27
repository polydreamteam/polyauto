package com.polyauto.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.polyauto.auth.Authenticator;
import com.polyauto.entities.BookingsEntity;
import com.polyauto.entities.CarsEntity;
import com.polyauto.exceptions.BadRequestException;
import com.polyauto.exceptions.UnauthorizedException;
import com.polyauto.repositories.BookingsEntityRepository;
import com.polyauto.repositories.CarsEntityRepository;
import com.polyauto.utilities.DbManager;
import com.polyauto.utilities.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.sql.Date;
import java.util.List;

@RestController
public class BookingsController
{
    @Autowired
    private BookingsEntityRepository bookingsRepository;

    @Autowired
    private CarsEntityRepository carsRepository;

    @RequestMapping(method = RequestMethod.GET,value="/getLastOpenedBooking",produces="application/json")
    public GenericResponse getLastOpenedBooking(@RequestParam String token) throws RuntimeException
    {
        DecodedJWT jwt_decoded = Authenticator.verifyAndDecodeToken(token);
        int userId = Integer.parseInt(jwt_decoded.getClaim("userId").asString());

        List<BookingsEntity> list = bookingsRepository.findUsersOpenedBooking(userId);

        GenericResponse response = new GenericResponse();

        BookingsEntity booking = list.size() > 0 ? list.get(0) : null;

        response.addToContent("booking",booking);
        return response;
    }

    @RequestMapping(method = RequestMethod.POST,value="/bookCar",produces="application/json")
    public GenericResponse bookCar(@RequestParam String token,@RequestParam String userId,@RequestParam String idCar) throws Exception
    {
        DecodedJWT jwt_decoded = Authenticator.verifyAndDecodeToken(token);
        int tokenUserId = Integer.parseInt(jwt_decoded.getClaim("userId").asString());

        if(tokenUserId != Integer.valueOf(userId))
        {
            throw new UnauthorizedException();
        }

        //TODO Mettre le lien API JBOSS

        CarsEntity car = carsRepository.isCarAvalaible(Integer.valueOf(idCar));

        if(car == null)
        {
            throw new BadRequestException();
        }

        BookingsEntity newBooking = new BookingsEntity();
        newBooking.setIdUser(Integer.valueOf(userId));
        newBooking.setIdCar(Integer.valueOf(idCar));
        newBooking.setStatus(Byte.parseByte("1"));

        java.util.Date utilDate = new java.util.Date();
        newBooking.setDateUp(new Date(utilDate.getTime()));

        DbManager.saveBooking(newBooking);

        GenericResponse response = new GenericResponse();
        return response;
    }
}
