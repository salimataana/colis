package com.github.ana.deliverymanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class DeliveryManagementController {
    @RequestMapping(value="/home",method= RequestMethod.GET)
    public String getHome(){
        return "home";
    }
    @RequestMapping(value="/trackingpacket",method= RequestMethod.GET)
    public String getTrackingPacket(Integer id){
        return "trackingpacket";
    }
}
