package com.github.ana.deliverymanagement.controllers;


import com.github.ana.deliverymanagement.models.Packet;
import com.github.ana.deliverymanagement.repository.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PacketController {

    @Autowired
    private PacketRepository packetRepository;

    @RequestMapping(value="/packet",method= RequestMethod.GET)
    public String index(Model model) {
        List<Packet> packets = new ArrayList<>();
        packetRepository.findAll().forEach(packets::add);
        model.addAttribute("packets", packets);
        return "indexPacket";

    }
    @RequestMapping(value="/packet/create",method= RequestMethod.GET)
    public String create(Model model){
        //model.addAttribute("packet", new Packet()); // A
        return "createPacket";
    }

    @RequestMapping(value="/packet",method= RequestMethod.POST)
    public ModelAndView store (@RequestParam("name") String name, @RequestParam("address_packet") String address_packet,
                               @RequestParam("date_depart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date_depart){
        System.out.println("je suis dans store");
        Packet packet = new Packet();
        packet.setName(name);
        packet.setAddress_packet(address_packet);
        packet.setDate_depart(date_depart);
        packetRepository.save(packet);
        return new ModelAndView("redirect:/packet");
    }
    @RequestMapping(value="/packet/{id}",method= RequestMethod.GET)
    public String show(Integer id){
        Optional<Packet> packet= packetRepository.findById(id);
        return "showPacket";
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

