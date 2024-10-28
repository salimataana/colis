package com.github.ana.deliverymanagement.controllers;
import com.github.ana.deliverymanagement.models.Geolocation;
import com.github.ana.deliverymanagement.models.Packet;
import com.github.ana.deliverymanagement.repository.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
        return "createPacket";
    }

    @RequestMapping(value="/packet",method= RequestMethod.POST)
    public ModelAndView store (@RequestParam("name") String name, @RequestParam("address_packet") String address_packet,
                               @RequestParam("weight") Double weight,
                               @RequestParam("date_depart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date_depart,
                               @RequestParam("date_arrival") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date_arrival){
        System.out.println("je suis dans store");
        Packet packet = new Packet();
        packet.setName(name);
        packet.setWeight(weight);
        packet.setAddress_packet(address_packet);
        packet.setDate_depart(date_depart);
        packet.setDate_arrival(date_arrival);
        packetRepository.save(packet);
        return new ModelAndView("redirect:/packet");
    }
    @RequestMapping(value="/packet/{id}",method= RequestMethod.GET)
    public String show(Integer id){
        Optional<Packet> packet= packetRepository.findById(id);
        return "showPacket";
    }

    @RequestMapping(value="/packet",method= RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestParam Integer id, @RequestBody Packet updatedPacket) {
        Optional<Packet> existingPacketOpt = packetRepository.findById(id);
        if (existingPacketOpt.isPresent()) {
            Packet existingPacket = existingPacketOpt.get();
            existingPacket.setName(updatedPacket.getName());
            existingPacket.setAddress_packet(updatedPacket.getAddress_packet());
            existingPacket.setDate_depart(updatedPacket.getDate_depart());
            existingPacket.setDate_arrival(updatedPacket.getDate_arrival());
            existingPacket.setWeight(updatedPacket.getWeight());
            packetRepository.save(existingPacket);
            return ResponseEntity.ok("Packet updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(value="/packet",method= RequestMethod.DELETE)
    public String delete(Integer id){
        packetRepository.deleteById(id);
        return "redirect:/packet";
    }
}

