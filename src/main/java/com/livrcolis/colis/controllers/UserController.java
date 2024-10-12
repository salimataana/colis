package com.livrcolis.colis.controllers;


import com.livrcolis.colis.models.User;
import com.livrcolis.colis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    @Autowired
    private UserRepository usersRepository;

    @RequestMapping(value="/users",method= RequestMethod.GET)
    public String index() {
        return "index";

    }
    @RequestMapping(value="/users/create",method= RequestMethod.GET)
    public String create(){
        return "createUsers";
    }

    @RequestMapping(value="/users",method= RequestMethod.POST)
    public String store (@RequestParam("name") String name, @RequestParam("email") String email){
       User users = new User();
        users.setName(name);
        users.setEmail(email);
        usersRepository.save(users);
        return "redirect:/users";
    }
    @RequestMapping(value="/users/{id}",method= RequestMethod.GET)
    public void show(Integer id){

    }
    @RequestMapping(value="/users",method= RequestMethod.PUT)
    public void update(){

    }
    @RequestMapping(value="/users",method= RequestMethod.DELETE)
    public void delete(){

    }
}
