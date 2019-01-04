package com.lr.mule.lesson03.rest.service;

import java.util.List;

import javax.jws.WebService;

import com.lr.mule.lesson03.rest.model.Brew;

public interface BrewService {
    List<Brew> getBrews();
}
