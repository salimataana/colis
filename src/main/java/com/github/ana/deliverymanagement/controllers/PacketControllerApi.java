package com.github.ana.deliverymanagement.controllers;
import com.github.ana.deliverymanagement.models.Packet;
import com.github.ana.deliverymanagement.models.Users;
import com.github.ana.deliverymanagement.repository.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RequestMapping(value="/rest/v1")
@RestController
public class PacketControllerApi {

    @Autowired
    private PacketRepository packetRepository;

    private List<Users> users_deliver;

    public PacketControllerApi() {
        this.users_deliver = new ArrayList<>();

    }
    @CrossOrigin("http://localhost:5173")
    @ResponseBody
    @RequestMapping(value = "/packet", method = RequestMethod.GET)
    public List<Packet> index() {
        List<Packet> packets = new ArrayList<>();
        packetRepository.findAll().forEach(packets::add);
       return packets;
    }

    @RequestMapping(value = "/packet/create", method = RequestMethod.GET)
    public String create(Model model) {

        return "createPacket";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test() {
        System.out.println("je suis dans le test");
        return "createPacket";
    }
    @CrossOrigin("http://localhost:5173")
    @RequestMapping(value = "/packet", method = RequestMethod.POST)
    public ResponseEntity<String> store(@RequestBody  Packet packet) {
        System.out.println("je suis dans store");

        packet.setUsers_deliver(this.getRandomUserForDelivery());
        packetRepository.save(packet);
        return ResponseEntity.ok("Packet updated successfully.");
    }

    @RequestMapping(value = "/packet/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Integer id, Model model) {
        Optional<Packet> packet = packetRepository.findById(id);
        return "showPacket";
    }

    @RequestMapping(value = "/packet", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestParam Integer id, @RequestBody Packet updatedPacket) {
        Optional<Packet> existingPacketOpt = packetRepository.findById(id);
        if (existingPacketOpt.isPresent()) {
            Packet existingPacket = existingPacketOpt.get();
            existingPacket.setName(updatedPacket.getName());
            existingPacket.setAddress_packet(updatedPacket.getAddress_packet());
            existingPacket.setDate_creation(updatedPacket.getDate_creation());
            existingPacket.setCode_postal(updatedPacket.getCode_postal());
            existingPacket.setWeight(updatedPacket.getWeight());
            packetRepository.save(existingPacket);
            return ResponseEntity.ok("Packet updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(value = "/packet", method = RequestMethod.DELETE)
    public String delete(Integer id) {
        packetRepository.deleteById(id);
        return "redirect:/packet";
    }

    public Users getRandomUserForDelivery() {
        if (users_deliver == null || users_deliver.isEmpty()) {
            return null; //
        }
        Random random = new Random();
        int randomIndex = random.nextInt(users_deliver.size());
        return users_deliver.get(randomIndex);
    }
}

