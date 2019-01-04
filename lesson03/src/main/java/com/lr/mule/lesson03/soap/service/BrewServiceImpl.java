package com.lr.mule.lesson03.soap.service;



import javax.jws.WebService;

import com.lr.mule.lesson03.soap.model.Brew;

import java.util.List;

@WebService(endpointInterface = "com.lr.mule.lesson03.soap.service.BrewService", serviceName = "BrewService")
public class BrewServiceImpl implements BrewService {
    public List<Brew> getBrews() {
        return Brew.findAll();
    }
}

