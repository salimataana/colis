package com.github.ana.deliverymanagement.controllers;


import com.github.ana.deliverymanagement.models.Packet;
import com.github.ana.deliverymanagement.repository.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;
import java.util.Optional;

@Controller
public class PacketController {

    @Autowired
    private PacketRepository packetRepository;

    @RequestMapping(value="/packet",method= RequestMethod.GET)
    public String index() {
        return "index";

    }
    @RequestMapping(value="/packet/create",method= RequestMethod.GET)
    public String create(){
        return "createPacket";
    }

    @RequestMapping(value="/packet",method= RequestMethod.POST)
    public String store (@RequestParam("name") String name, @RequestParam("address_packet") String address_packet,  @RequestParam("date_depart") Date date_depart){
        Packet packet = new Packet();
        packet.setName(name);
        packet.setAddress_packet(address_packet);
        packet.setDate_depart(date_depart);
        packetRepository.save(packet);
        return "redirect:/packet";
    }
    @RequestMapping(value="/packet/{id}",method= RequestMethod.GET)
    public void show(Integer id){
        Optional<Packet> packet= packetRepository.findById(id);
    }
    @RequestMapping(value="/packet",method= RequestMethod.PUT)
    public void update(){

    }
    @RequestMapping(value="/packet",method= RequestMethod.DELETE)
    public String delete(Integer id){
        packetRepository.deleteById(id);
        return "redirect:/packet";
    }
}

