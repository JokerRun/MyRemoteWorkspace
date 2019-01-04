package com.lr.mule.lesson03.rest.service;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.lr.mule.lesson03.rest.model.Brew;

import java.util.List;

@Path("/brews")
public class BrewServiceImpl implements BrewService {
    @GET
    @Produces("application/json")
    public List<Brew> getBrews() {
        return Brew.findAll();
    }
}

