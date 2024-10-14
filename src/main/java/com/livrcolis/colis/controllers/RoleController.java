package com.livrcolis.colis.controllers;

import com.livrcolis.colis.models.Role;
import com.livrcolis.colis.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
public class RoleController {

        @Autowired
        private RoleRepository roleRepository;


        @RequestMapping(value="/role",method= RequestMethod.GET)
        public String index() {
            return "index";

        }
        @RequestMapping(value="/role/create",method= RequestMethod.GET)
        public String create(){
            return "create";
        }

        @RequestMapping(value="/role",method= RequestMethod.POST)
        public String store (@RequestParam("name") String name, @RequestParam("description") String description){
           Role role = new Role();
            role.setName(name);
            role.setDesciption(description);;
            roleRepository.save(role);
            return "redirect:/role";
        }
        @RequestMapping(value="/role/{id}",method= RequestMethod.GET)
        public void show(Integer id){
            Optional<Role> role= roleRepository.findById(id);

        }
        @RequestMapping(value="/role",method= RequestMethod.PUT)
        public void update(){

        }
        @RequestMapping(value="/role",method= RequestMethod.DELETE)
        public String delete(Integer id){
            roleRepository.deleteById(id);
            return "redirect:/role";
        }
    }


