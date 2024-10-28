package com.github.ana.deliverymanagement.controllers;

import com.github.ana.deliverymanagement.models.Packet;
import com.github.ana.deliverymanagement.models.PacketStatus;
import com.github.ana.deliverymanagement.models.Role;
import com.github.ana.deliverymanagement.repository.RoleRepository;
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
import java.util.List;
import java.util.Optional;

@Controller
public class RoleController {

        @Autowired
        private RoleRepository roleRepository;


        @RequestMapping(value="/role",method= RequestMethod.GET)
        public String index(Model model) {
            List<Role> roles = new ArrayList<>();
            roleRepository.findAll().forEach(roles::add);
            model.addAttribute("roles", roles);
            return "indexRole";

        }
        @RequestMapping(value="/role/create",method= RequestMethod.GET)
        public String create(Model model){
            return "createRole";
        }

        @RequestMapping(value="/role",method= RequestMethod.POST)
        public ModelAndView store (@RequestParam("name") String name, @RequestParam("description") String description){
            Role role = new Role();
            role.setName(name);
            role.setDescription(description);
            roleRepository.save(role);
            return new ModelAndView("redirect:/role");
        }
        @RequestMapping(value="/role/{id}",method= RequestMethod.GET)
        public void show(Integer id){
            Optional<Role> role= roleRepository.findById(id);

        }
        @RequestMapping(value="/role",method= RequestMethod.PUT)
        public ResponseEntity<String> update(@RequestParam Integer id, @RequestBody Role updatedRole) {
            Optional<Role> existingRoleOpt = roleRepository.findById(id);
            if (existingRoleOpt.isPresent()) {
                Role existingRole= existingRoleOpt.get();
                existingRole.setName(updatedRole.getName());
                existingRole.setDescription(updatedRole.getDescription());
                roleRepository.save(existingRole);
                return ResponseEntity.ok("Role updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        @RequestMapping(value="/role",method= RequestMethod.DELETE)
        public String delete(Integer id){
            roleRepository.deleteById(id);
            return "redirect:/role";
        }
    }


