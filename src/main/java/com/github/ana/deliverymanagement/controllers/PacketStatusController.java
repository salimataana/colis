package com.github.ana.deliverymanagement.controllers;
import com.github.ana.deliverymanagement.models.PacketStatus;
import com.github.ana.deliverymanagement.repository.PacketStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class PacketStatusController {


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
        return "createPacketStatus";
    }

    @RequestMapping(value="/packetstatus",method= RequestMethod.POST)
    public ModelAndView store (@RequestParam("name") String name, @RequestParam("description") String description){
        PacketStatus packetstatus = new PacketStatus();
        packetstatus.setName(name);
        packetstatus.setDescription(description);
        packetstatusRepository.save(packetstatus);
        return new ModelAndView("redirect:/packetstatus");
    }
    @RequestMapping(value="/packetstatus/{id}",method= RequestMethod.GET)
    public String show(Integer id){
        Optional<PacketStatus> packetstatus= packetstatusRepository.findById(id);
        return "showPacketStatus";

    }
    @RequestMapping(value="/packetstatus",method= RequestMethod.PUT)
    public void update(){

    }
    @RequestMapping(value="/packetstatus",method= RequestMethod.DELETE)
    public String delete(Integer id){
        packetstatusRepository.deleteById(id);
        return "redirect:/packetstatus";

    }
}

