package com.livrcolis.colis.controllers;

import com.livrcolis.colis.models.Geolocalisation;
import com.livrcolis.colis.repository.GeolocalisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class GeolocalisationController {

    @Autowired
    private GeolocalisationRepository geolocalisationRepository;


    @RequestMapping(value="/geolocalisation",method= RequestMethod.GET)
    public String index() {
        return "index";

    }
    @RequestMapping(value="/geolocalisation/create",method= RequestMethod.GET)
    public String create(){
        return "create";
    }

    @RequestMapping(value="/geolocalistion",method= RequestMethod.POST)
    public String store (@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude){
       Geolocalisation geolocalisation = new Geolocalisation();
        geolocalisation.setLatitude(latitude);
        geolocalisation.setLongitude(longitude);;
        geolocalisationRepository.save(geolocalisation);
        return "redirect:/geolocalisation";
    }
    @RequestMapping(value="/geolocalisation/{id}",method= RequestMethod.GET)
    public void show(Integer id){

    }
    @RequestMapping(value="/geolocalisation",method= RequestMethod.PUT)
    public void update(){

    }
    @RequestMapping(value="/geolocalisation",method= RequestMethod.DELETE)
    public void delete(){

    }
}
