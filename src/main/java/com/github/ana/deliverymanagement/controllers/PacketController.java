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

@Controller
public class PacketController {

    @Autowired
    private PacketRepository packetRepository;

    private List<Users> users_deliver;

    public PacketController() {
        this.users_deliver = new ArrayList<>();

    }


    @RequestMapping(value = "/packet", method = RequestMethod.GET)
    public String index(Model model) {
        List<Packet> packets = new ArrayList<>();
        packetRepository.findAll().forEach(packets::add);
        model.addAttribute("packets", packets);
        return "indexPacket";

    }

    @RequestMapping(value = "/packet/create", method = RequestMethod.GET)
    public String create(Model model) {

        return "createPacket";
    }

    @RequestMapping(value = "/packet", method = RequestMethod.POST)
    public ModelAndView store(@RequestParam("name") String name, @RequestParam("address_packet") String address_packet,
                              @RequestParam("weight") Double weight,
                              @RequestParam("code_postal") Integer code_postal,
                              @RequestParam("date_creation") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date_creation) {
        System.out.println("je suis dans store");
        Packet packet = new Packet();
        packet.setName(name);
        packet.setWeight(weight);
        packet.setAddress_packet(address_packet);
        packet.setCode_postal(code_postal);
        packet.setDate_creation(date_creation);
        packet.setUsers_deliver(this.getRandomUserForDelivery());
        packetRepository.save(packet);
        return new ModelAndView("redirect:/packet");
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

