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
public class PacketStatusController {

    @Autowired
    private PacketRepository packetRepository;

    @Autowired
    private PacketStatusRepository packetstatusRepository;


    @RequestMapping(value="/packetstatus",method= RequestMethod.GET)
    public String index(Model model) {
        List<PacketStatus> packetstatus = new ArrayList<>();
        packetstatusRepository.findAll().forEach(packetstatus::add);
        model.addAttribute("packetstatus", packetstatus);
        return "indexPacketStatus";

    }
    @RequestMapping(value="/packetstatus/create",method= RequestMethod.GET)
    public String create(Model model){
        List<Packet> packets = new ArrayList<>();
        packetRepository.findAll().forEach(packets::add);
        model.addAttribute("packets", packets);
        return "createPacketStatus";
    }

    @RequestMapping(value="/packetstatus",method= RequestMethod.POST)
    public ModelAndView store (@RequestParam("name") String name, @RequestParam("description") String description,
                               @RequestParam("packet") Integer packet_id){
        Optional<Packet> packet = packetRepository.findById(packet_id);
        PacketStatus packetstatus = new PacketStatus();
        packetstatus.setName(name);
        packetstatus.setDescription(description);
        packetstatus.setCreated_at(new Date());
        packetstatus.setPacket(packet.get());
        packetstatusRepository.save(packetstatus);
        return new ModelAndView("redirect:/trackingpacket/" +packet_id);
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

