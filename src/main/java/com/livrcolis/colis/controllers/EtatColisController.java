package com.livrcolis.colis.controllers;


import com.livrcolis.colis.models.EtatColis;
import com.livrcolis.colis.repository.EtatColisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EtatColisController {


    @Autowired
    private EtatColisRepository etatcolisRepository;


    @RequestMapping(value="/etatcolis",method= RequestMethod.GET)
    public String index() {
        return "index";

    }
    @RequestMapping(value="/etatcolis/create",method= RequestMethod.GET)
    public String create(){
        return "create";
    }

    @RequestMapping(value="/etatcolis",method= RequestMethod.POST)
    public String store (@RequestParam("name") String name, @RequestParam("description") String description){
       EtatColis etatcolis = new EtatColis();
       etatcolis.setName(name);
       etatcolis.setDesciption(description);;
       etatcolisRepository.save(etatcolis);
        return "redirect:/etatcolis";
    }
    @RequestMapping(value="/etatcolis/{id}",method= RequestMethod.GET)
    public void show(Integer id){

    }
    @RequestMapping(value="/etatcolis",method= RequestMethod.PUT)
    public void update(){

    }
    @RequestMapping(value="/etatcolis",method= RequestMethod.DELETE)
    public void delete(){

    }
}

