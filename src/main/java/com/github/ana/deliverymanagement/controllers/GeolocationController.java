package com.github.ana.deliverymanagement.controllers;
import com.github.ana.deliverymanagement.models.Geolocation;
import com.github.ana.deliverymanagement.repository.GeolocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class GeolocationController {

    @Autowired
    private GeolocationRepository geolocationRepository;


    @RequestMapping(value="/geolocation",method= RequestMethod.GET)
    public String index(Model model) {
        List<Geolocation> geolocations = new ArrayList<>();
        geolocationRepository.findAll().forEach(geolocations::add);
        model.addAttribute("geolocations", geolocations);
        return "indexGeolocation";

    }
    @RequestMapping(value="/geolocation/create",method= RequestMethod.GET)
    public String create(Model model){
        return "geolocationdelivery";
    }

    @RequestMapping(value="/geolocation",method= RequestMethod.POST)
    public ModelAndView store (@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude,
                               @RequestParam("description") String description){
        Geolocation geolocation = new Geolocation();
        geolocation.setLatitude(latitude);
        geolocation.setLongitude(longitude);
        geolocation.setDescription(description);
        geolocationRepository.save(geolocation);
        return new ModelAndView("redirect:/geolocation");
    }
    @RequestMapping(value="/geolocation/{id}",method= RequestMethod.GET)
    public void show(Integer id){
        Optional<Geolocation> geolocation= geolocationRepository.findById(id);
    }

    @RequestMapping(value="/geolocation",method= RequestMethod.PUT)
        public ResponseEntity<String> update(@RequestParam Integer id, @RequestBody Geolocation updatedGeolocation) {
            Optional<Geolocation> existingGeolocationOpt = geolocationRepository.findById(id);
            if (existingGeolocationOpt.isPresent()) {
                Geolocation existingGeolocation = existingGeolocationOpt.get();
                existingGeolocation.setLatitude(updatedGeolocation.getLatitude());
                existingGeolocation.setLongitude(updatedGeolocation.getLongitude());
                existingGeolocation.setDescription(updatedGeolocation.getDescription());
                geolocationRepository.save(existingGeolocation);
                return ResponseEntity.ok("Geolocation updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }

    @RequestMapping(value="/geolocation",method= RequestMethod.DELETE)
    public String delete(Integer id){
        geolocationRepository.deleteById(id);
        return "redirect:/geolocation";
    }
}

