package com.polyauto.controller;
import com.polyauto.entities.*;
import com.polyauto.repositories.*;

import com.polyauto.utilities.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarsController
{
    @Autowired
    private CarsEntityRepository carsRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET,value="/getAvailableCars",produces="application/json")
    public GenericResponse getAvailableCars()
    {
        List<CarsEntity> list = carsRepository.findAvailableCars();

        GenericResponse response = new GenericResponse();
        response.addToContent("avalaibleCars",list);
        return response;
    }
}
