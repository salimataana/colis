package com.github.ana.deliverymanagement.controllers;

import com.github.ana.deliverymanagement.models.Packet;
import com.github.ana.deliverymanagement.models.PacketStatus;
import com.github.ana.deliverymanagement.repository.PacketRepository;
import com.github.ana.deliverymanagement.repository.PacketStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RequestMapping(value="/rest/v1")
@RestController
public class PacketStatusControllerApi {

    @Autowired
    private PacketRepository packetRepository;

    @Autowired
    private PacketStatusRepository packetstatusRepository;

    @CrossOrigin("http://localhost:5173")
    @RequestMapping(value="/packetstatus",method= RequestMethod.GET)
    public List<PacketStatus> index() {
        List<PacketStatus> packetstatus = new ArrayList<>();
        packetstatusRepository.findAll().forEach(packetstatus::add);
        return packetstatus;

    }
    @CrossOrigin("http://localhost:5173")
    @RequestMapping(value="/packetstatus/create",method= RequestMethod.GET)
    public List<Packet> create(){
        List<Packet> packets = new ArrayList<>();
        packetRepository.findAll().forEach(packets::add);
        return packets;
    }
    @CrossOrigin("http://localhost:5173")
    @RequestMapping(value="/packetstatus",method= RequestMethod.POST)
    public  ResponseEntity<String> store (@RequestBody PacketStatus packetstatus,
                               @RequestParam("packet") Integer packet_id){
        Optional<Packet> packet = packetRepository.findById(packet_id);
        packetstatus.setCreated_at(new Date());
        packetstatus.setPacket(packet.get());
        packetstatusRepository.save(packetstatus);
        return ResponseEntity.ok("Packet Status updated successfully.");
    }
    @RequestMapping(value="/packetstatus/{id}",method= RequestMethod.GET)
    public String show(Integer id){
        Optional<PacketStatus> packetstatus= packetstatusRepository.findById(id);
        return "showPacketStatus";

    }
    @RequestMapping(value="/packetstatus",method= RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestParam Integer id, @RequestBody PacketStatus updatedPacketStatus) {
           Optional<PacketStatus> existingPacketStatusOpt = packetstatusRepository.findById(id);
        if (existingPacketStatusOpt.isPresent()) {
            PacketStatus existingPacketStatus = existingPacketStatusOpt.get();
            existingPacketStatus.setName(updatedPacketStatus.getName());
            existingPacketStatus.setDescription(updatedPacketStatus.getDescription());
            packetstatusRepository.save(existingPacketStatus);
            return ResponseEntity.ok("PacketStatus updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @RequestMapping(value="/packetstatus",method= RequestMethod.DELETE)
    public String delete(Integer id){
        packetstatusRepository.deleteById(id);
        return "redirect:/packetstatus";

    }
}

