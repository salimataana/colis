package com.github.ana.deliverymanagement.controllers;

import com.github.ana.deliverymanagement.models.Packet;
import com.github.ana.deliverymanagement.models.PacketStatus;
import com.github.ana.deliverymanagement.repository.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RequestMapping(value="/rest/v1")
@RestController
public class DeliveryManagementControllerApi {

    @Autowired
    PacketRepository repository;

    @RequestMapping(value="/home",method= RequestMethod.GET)
    public String getHome(){

        return "home";
    }

    @CrossOrigin("http://localhost:5173")
    @GetMapping("/trackingpacket/{id}")
    public ResponseEntity<List<PacketStatus>> getTrackingPacket(@PathVariable("id") Integer id) {
        Optional<Packet> packet = repository.findById(id);
        if (packet.isPresent()) {
            List<PacketStatus> packetStatuses = packet.get().getPacketstatus();
            return ResponseEntity.ok(packetStatuses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



