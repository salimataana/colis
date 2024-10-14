package com.livrcolis.colis.controllers;

import com.livrcolis.colis.models.Geolocation;
import com.livrcolis.colis.repository.GeolocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;


@Controller
public class GeolocationController {

    @Autowired
    private GeolocationRepository geolocationRepository;


    @RequestMapping(value="/geolocation",method= RequestMethod.GET)
    public String index() {
        return "index";

    }
    @RequestMapping(value="/geolocation/create",method= RequestMethod.GET)
    public String create(){

        return "create";
    }

    @RequestMapping(value="/geolocation",method= RequestMethod.POST)
    public String store (@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude){
        Geolocation geolocation = new Geolocation();
        geolocation.setLatitude(latitude);
        geolocation.setLongitude(longitude);;
        geolocationRepository.save(geolocation);
        return "redirect:/geolocation";
    }
    @RequestMapping(value="/geolocation/{id}",method= RequestMethod.GET)
    public void show(Integer id){
        Optional<Geolocation> geolocation= geolocationRepository.findById(id);
    }
    @RequestMapping(value="/geolocation",method= RequestMethod.PUT)
    public void update(){

    }
    @RequestMapping(value="/geolocation",method= RequestMethod.DELETE)
    public String delete(Integer id){
        geolocationRepository.deleteById(id);
        return "redirect:/geolocation";
    }
}
