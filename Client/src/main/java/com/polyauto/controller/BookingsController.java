package com.polyauto.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.polyauto.auth.Authenticator;
import com.polyauto.dto.ObjectMessageSend;
import com.polyauto.entities.Bookings;
import com.polyauto.entities.BookingsEntity;
import com.polyauto.entities.CarsEntity;
import com.polyauto.exceptions.BadRequestException;
import com.polyauto.exceptions.UnauthorizedException;
import com.polyauto.jboss.TopicPoster;
import com.polyauto.repositories.BookingsEntityRepository;
import com.polyauto.repositories.CarsEntityRepository;
import com.polyauto.utilities.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.ObjectMessage;
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
        System.out.println(Integer.valueOf(idCar));
        System.out.println(userId);
        System.out.println("42424242");
        if(tokenUserId != Integer.valueOf(userId))
        {
            throw new UnauthorizedException();
        }

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

        //TODO Mettre le lien API JBOSS
        //TODO : Statut voiture
        Bookings book = new Bookings();
        book.setDateUp(new Date(utilDate.getTime()));
        book.setIdUser(Integer.valueOf(userId));
        book.setIdCar(Integer.valueOf(idCar));
        book.setStatus(Byte.parseByte("1"));
        ObjectMessageSend toSend = new ObjectMessageSend(ObjectMessageSend.INSERT, book);
        TopicPoster.publish(toSend);

        //DbManager.saveBooking(newBooking);

        GenericResponse response = new GenericResponse();

        response.addToContent("booking", newBooking);

        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value="/closeBooking",produces="application/json")
    public GenericResponse closeBooking(@RequestParam String token, @RequestParam String bookingId) throws Exception
    {
        //Identify and get user id
        DecodedJWT jwt_decoded = Authenticator.verifyAndDecodeToken(token);
        int userId = Integer.parseInt(jwt_decoded.getClaim("userId").asString());

        //Find book by user id
        BookingsEntity booking = bookingsRepository.findByIdBooking(Integer.valueOf(bookingId));

        //Error if not existent
        if (booking == null)
        {
            throw new BadRequestException();
        }

        List<BookingsEntity> userOpenedBookings = bookingsRepository.findUsersOpenedBooking(userId);

        //Error if not opened by user
        if (!userOpenedBookings.contains(booking))
        {
            throw new BadRequestException();
        }

        //Close the booking
        booking.setStatus(Byte.parseByte("0"));

        java.util.Date utilDate = new java.util.Date();
        booking.setDateDown(new Date(utilDate.getTime()));

        Bookings book = new Bookings();
        book.setStatus(Byte.parseByte("0"));
        book.setIdCar(booking.getIdCar());
        book.setIdUser(booking.getIdUser());
        book.setDateUp(booking.getDateUp());
        book.setDateDown(new Date(utilDate.getTime()));
        book.setIdBooking(booking.getIdBooking());

        ObjectMessageSend toSend = new ObjectMessageSend(ObjectMessageSend.UPDATE, book);
        TopicPoster.publish(toSend);


        GenericResponse response = new GenericResponse();

        response.addToContent("booking", booking);

        return response;
    }
}
