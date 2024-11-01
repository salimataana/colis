package com.github.ana.deliverymanagement.controllers;

import com.github.ana.deliverymanagement.models.Packet;
import com.github.ana.deliverymanagement.models.PacketStatus;
import com.github.ana.deliverymanagement.repository.PacketRepository;
import com.github.ana.deliverymanagement.repository.PacketStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class DeliveryManagementController {

    @Autowired
    PacketRepository repository;

    @RequestMapping(value="/home",method= RequestMethod.GET)
    public String getHome(){

        return "home";
    }
    @RequestMapping(value="/trackingpacket/{id}",method= RequestMethod.GET)
    public String getTrackingPacket(@PathVariable("id") Integer id, Model model){
        Optional<Packet> packet = repository.findById(id);
        List <PacketStatus> packetStatuses = packet.get().getPacketstatus();
        model.addAttribute("packetStatuses", packetStatuses);
        return "trackingpacket";
    }
}
