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

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET,value="/getAvailableCars",produces="application/json")
    public GenericResponse getAvailableCars()
    {
        List<CarsEntity> list = carsRepository.findAvailableCars();

        GenericResponse response = new GenericResponse();
        response.addToContent("cars",list);
        return response;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET,value="/getNotAvailableCars",produces="application/json")
    public GenericResponse getNotAvailableCars()
    {
        List<CarsEntity> list = carsRepository.findNotAvailableCars();

        GenericResponse response = new GenericResponse();
        response.addToContent("cars",list);
        return response;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET,value="/getWithStatusAndModel",produces="application/json")
    public GenericResponse getWithStatusAndModel(String status, String model)
    {
        List<CarsEntity> list = carsRepository.findWithStatusAndModel(Byte.valueOf(status), Integer.parseInt(model));

        GenericResponse response = new GenericResponse();
        response.addToContent("cars",list);
        return response;
    }
}
