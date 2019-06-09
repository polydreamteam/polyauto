package com.polyauto.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.polyauto.auth.Authenticator;
import com.polyauto.utilities.GenericResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AdminController
{
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET,value="/isAdmin",produces="application/json")
    public GenericResponse login(@RequestParam String token) throws RuntimeException
    {
        DecodedJWT jwt_decoded = Authenticator.verifyAndDecodeToken(token);
        String isAdmin = jwt_decoded.getClaim("isAdmin").asString();

        GenericResponse response = new GenericResponse();
        response.addToContent("isAdmin",isAdmin);
        return response;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET,value="/admin",produces="application/json")
    public ModelAndView adminLobby() throws RuntimeException
    {
        return new ModelAndView("index");
    }
}
