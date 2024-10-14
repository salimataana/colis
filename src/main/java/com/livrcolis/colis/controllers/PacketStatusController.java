package com.livrcolis.colis.controllers;


import com.livrcolis.colis.models.PacketStatus;
import com.livrcolis.colis.repository.PacketStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PacketStatusController {


    @Autowired
    private PacketStatusRepository packetstatusRepository;


    @RequestMapping(value="/packetstatus",method= RequestMethod.GET)
    public String index() {
        return "index";

    }
    @RequestMapping(value="/packetstatus/create",method= RequestMethod.GET)
    public String create(){
        return "create";
    }

    @RequestMapping(value="/packetstatus",method= RequestMethod.POST)
    public String store (@RequestParam("name") String name, @RequestParam("description") String description){
       PacketStatus packetstatus = new PacketStatus();
        packetstatus.setName(name);
        packetstatus.setDesciption(description);;
        packetstatusRepository.save(packetstatus);
        return "redirect:/packetstatus";
    }
    @RequestMapping(value="/packetstatus/{id}",method= RequestMethod.GET)
    public void show(Integer id){

    }
    @RequestMapping(value="/packetstatus",method= RequestMethod.PUT)
    public void update(){

    }
    @RequestMapping(value="/packetstatus",method= RequestMethod.DELETE)
    public void delete(){

    }
}

