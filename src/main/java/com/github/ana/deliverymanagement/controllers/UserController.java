package com.github.ana.deliverymanagement.controllers;

import com.github.ana.deliverymanagement.models.User;
import com.github.ana.deliverymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Optional;


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
    public String store (@RequestParam("name") String name, @RequestParam("email") String email,
                         @RequestParam("address_user") String address_user,
                         @RequestParam("phoneNumber") String phoneNumber){
        User users = new User();
        users.setName(name);
        users.setEmail(email);
        users.setAddress_user(address_user);
        users.setPhoneNumber(phoneNumber);
        usersRepository.save(users);
        return "redirect:/users";
    }
    @RequestMapping(value="/users/{id}",method= RequestMethod.GET)
    public void show(Integer id){
        Optional<User> users = usersRepository.findById(id);

    }
    @RequestMapping(value="/users",method= RequestMethod.PUT)
    public void update(){

    }
    @RequestMapping(value="/users",method= RequestMethod.DELETE)
    public String delete(Integer id){
        usersRepository.deleteById(id);
        return "redirect:/role";
    }
}
