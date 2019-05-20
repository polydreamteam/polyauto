package com.polyauto.controller;
import com.polyauto.entities.*;
import com.polyauto.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarsController
{
    @Autowired
    private CarsEntityRepository carsRepository;

    @RequestMapping(method = RequestMethod.GET,value="/getAvailableCars")
    public List<CarsEntity> getAvailableCars()
    {
        List<CarsEntity> list = carsRepository.findAvailableCars();
        return list;
    }
}
