package com.github.ana.deliverymanagement.controllers;
import com.github.ana.deliverymanagement.models.Role;
import com.github.ana.deliverymanagement.models.User;
import com.github.ana.deliverymanagement.repository.UserRepository;
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
public class UserController {

    @Autowired
    private UserRepository usersRepository;

    @RequestMapping(value="/users",method= RequestMethod.GET)
    public String index(Model model) {
        List<User> users = new ArrayList<>();
        usersRepository.findAll().forEach(users::add);
        model.addAttribute("users", users);
        return "indexUsers";

    }
    @RequestMapping(value="/users/create",method= RequestMethod.GET)
    public String create(Model model){
        return "createUsers";
    }

    @RequestMapping(value="/users",method= RequestMethod.POST)
    public ModelAndView store (@RequestParam("name") String name, @RequestParam("email") String email,
                               @RequestParam("address_user") String address_user,
                               @RequestParam("phoneNumber") String phoneNumber){
        User users = new User();
        users.setName(name);
        users.setEmail(email);
        users.setAddress_user(address_user);
        users.setPhoneNumber(phoneNumber);
        usersRepository.save(users);
        return new ModelAndView("redirect:/users");
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
        return "redirect:/users";
    }
}
