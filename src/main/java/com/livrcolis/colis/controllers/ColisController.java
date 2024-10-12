package com.livrcolis.colis.controllers;


import com.livrcolis.colis.models.Colis;
import com.livrcolis.colis.repository.ColisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;
import java.util.Optional;

@Controller
public class ColisController {

    @Autowired
    private ColisRepository colisRepository;

    @RequestMapping(value="/colis",method= RequestMethod.GET)
    public String index() {
        return "index";

    }
    @RequestMapping(value="/colis/create",method= RequestMethod.GET)
    public String create(){
        return "createColis";
    }

    @RequestMapping(value="/colis",method= RequestMethod.POST)
    public String store (@RequestParam("name") String name, @RequestParam("address_colis") String address_colis,  @RequestParam("date_depart") Date date_depart){
        Colis colis = new Colis();
        colis.setName(name);
        colis.setAddress_colis(address_colis);
        colis.setDate_depart(date_depart);
        colisRepository.save(colis);
        return "redirect:/colis";
    }
    @RequestMapping(value="/colis/{id}",method= RequestMethod.GET)
    public void show(Integer id){
       Optional<Colis> colis= colisRepository.findById(id);
    }
    @RequestMapping(value="/colis",method= RequestMethod.PUT)
    public void update(){

    }
    @RequestMapping(value="/colis",method= RequestMethod.DELETE)
    public String delete(Integer id){
      colisRepository.deleteById(id);
        return "redirect:/colis";
    }
}

