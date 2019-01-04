package com.lr.mule.lesson03.soap.service;

import java.util.List;

import javax.jws.WebService;

import com.lr.mule.lesson03.soap.model.Brew;

@WebService
public interface BrewService {
    List<Brew> getBrews();
}
