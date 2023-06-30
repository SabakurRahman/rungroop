package com.rungroop.webmvc.controller;

import com.rungroop.webmvc.dto.UserRegistationDto;
import com.rungroop.webmvc.model.UserEntity;
import com.rungroop.webmvc.service.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    UserEntityService userEntityService;

    @GetMapping("/register")
    public String getRegistationForm(Model model){

        UserRegistationDto userRegistationDto = new UserRegistationDto();
        model.addAttribute("user",userRegistationDto);
        return "registration-form";



    }
    @GetMapping("/login")
    public String getLoginPage(){
        return "login";

    }

    @PostMapping("/register/save")
    public String postRegistationForm(@Valid @ModelAttribute("user") UserRegistationDto userRegistationDto,
                                      BindingResult result,
                                      Model model){

        UserEntity existingUserEmail = userEntityService.findByEmail(userRegistationDto.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }
        UserEntity existingUserUsername = userEntityService.findByUsername(userRegistationDto.getUsername());
        if(existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
            return "redirect:/register?fail";
        }

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
        }


        if(result.hasErrors()){
            model.addAttribute("user",userRegistationDto);
            return "registration-form";
        }

        userEntityService.saveUser(userRegistationDto);
        return "redirect:/clubs?success";

    }
}
