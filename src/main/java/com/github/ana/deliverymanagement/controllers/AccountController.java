package com.github.ana.deliverymanagement.controllers;
import com.github.ana.deliverymanagement.dto.RegisterDto;
import com.github.ana.deliverymanagement.models.Users;
import com.github.ana.deliverymanagement.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Date;

@Controller
public class AccountController {
   @Autowired
   private UserRepository userRepository;

   @GetMapping("/register")
    public String register(Model model) {
       RegisterDto registerDto = new RegisterDto();
       model.addAttribute(registerDto);
       model.addAttribute("success", false);
       return "register";
   }


   @PostMapping("/register")
   public String register(
           Model model,
           @Valid  @ModelAttribute RegisterDto registerDto,
           BindingResult result
         ) {

      if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())){
         result.addError(
                 new FieldError("registerDto", "confirmPassword"
                    , "Password and Confirm Password do not match")
         );
      }

     Users users = userRepository.findByEmail(registerDto.getEmail());
        if (users != null){
         result.addError(
                 new FieldError("registerDto", "email"
                         , "Email address is already used")
         );
      }

        if (result.hasErrors()){
           return "register";
        }


        try {
           // create new account
           var bCryptEncoder = new BCryptPasswordEncoder();

           Users newUsers = new Users();
           newUsers.setFirstName(registerDto.getFirstName());
           newUsers.setLastName(registerDto.getLastName());
           newUsers.setEmail(registerDto.getEmail());
           newUsers.setPhone(registerDto.getPhone());
           newUsers.setAddress(registerDto.getAddress());
           newUsers.setRole("");
           newUsers.setCreatedAt(new Date());
           newUsers.setPassword(bCryptEncoder.encode(registerDto.getPassword()));

           userRepository.save(newUsers);

           model.addAttribute("registerDto", new RegisterDto());
           model.addAttribute("success", true);
        }
        catch (Exception ex){
           result.addError(
                   new FieldError("registerDto", "firstName"
                          , ex.getMessage())
           );
        }
      return "register";
   }
}
