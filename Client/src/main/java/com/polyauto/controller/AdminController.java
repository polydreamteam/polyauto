package com.polyauto.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.polyauto.auth.Authenticator;
import com.polyauto.entities.BookingsEntity;
import com.polyauto.entities.CarsEntity;
import com.polyauto.entities.UsersEntity;
import com.polyauto.exceptions.UnauthorizedException;
import com.polyauto.repositories.BookingsEntityRepository;
import com.polyauto.repositories.CarsEntityRepository;
import com.polyauto.repositories.UsersEntityRepository;
import com.polyauto.utilities.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class AdminController
{
    @Autowired
    private UsersEntityRepository usersRepository;

    @Autowired
    private CarsEntityRepository carsRepository;

    @Autowired
    private BookingsEntityRepository bookingsRepository;

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET,value="/isAdmin",produces="application/json")
    public GenericResponse isAdmin(@RequestParam String token) throws RuntimeException
    {
        DecodedJWT jwt_decoded = Authenticator.verifyAndDecodeToken(token);
        String isAdmin = jwt_decoded.getClaim("isAdmin").asString();

        GenericResponse response = new GenericResponse();
        response.addToContent("isAdmin",isAdmin);
        return response;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET,value="/admin")
    public ModelAndView adminLobby(HttpServletRequest request,@RequestParam String token) throws RuntimeException
    {
        DecodedJWT jwt_decoded = Authenticator.verifyAndDecodeToken(token);
        String isAdmin = jwt_decoded.getClaim("isAdmin").asString();

        if(!isAdmin.equals("1"))
        {
            throw new UnauthorizedException();
        }

        request.setAttribute("token",token);

        request.setAttribute("nbCars",carsRepository.count());
        request.setAttribute("nbUsers",usersRepository.count());
        request.setAttribute("nbBookings",bookingsRepository.count());

        return new ModelAndView("admin/index");
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET,value="/admin/cars")
    public ModelAndView carsList(HttpServletRequest request,@RequestParam String token) throws RuntimeException
    {
        DecodedJWT jwt_decoded = Authenticator.verifyAndDecodeToken(token);
        String isAdmin = jwt_decoded.getClaim("isAdmin").asString();

        if(!isAdmin.equals("1"))
        {
            throw new UnauthorizedException();
        }

        List<CarsEntity> carsEntityList = carsRepository.findAll();

        request.setAttribute("cars",carsEntityList);
        request.setAttribute("token",token);
        return new ModelAndView("admin/cars");
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET,value="/admin/users")
    public ModelAndView usersList(HttpServletRequest request,@RequestParam String token) throws RuntimeException
    {
        DecodedJWT jwt_decoded = Authenticator.verifyAndDecodeToken(token);
        String isAdmin = jwt_decoded.getClaim("isAdmin").asString();

        if(!isAdmin.equals("1"))
        {
            throw new UnauthorizedException();
        }

        List<UsersEntity> usersEntityList = usersRepository.findAll();

        request.setAttribute("users",usersEntityList);
        request.setAttribute("token",token);
        return new ModelAndView("admin/users");
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET,value="/admin/bookings")
    public ModelAndView bookingsList(HttpServletRequest request,@RequestParam String token) throws RuntimeException
    {
        DecodedJWT jwt_decoded = Authenticator.verifyAndDecodeToken(token);
        String isAdmin = jwt_decoded.getClaim("isAdmin").asString();

        if(!isAdmin.equals("1"))
        {
            throw new UnauthorizedException();
        }

        List<BookingsEntity> bookingsEntityList = bookingsRepository.findAll();

        request.setAttribute("bookings",bookingsEntityList);
        request.setAttribute("token",token);
        return new ModelAndView("admin/bookings");
    }
}
