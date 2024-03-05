package net.avishkar.springboot.controller;

import jakarta.validation.Valid;
import net.avishkar.springboot.dto.RegistrationDto;
import net.avishkar.springboot.entity.User;
import net.avishkar.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController
{
    private UserService userService;

    // Constructor based dependency injection
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle login page request
    @GetMapping("/login")
    public String loginPage()
    {
        return "login";
    }



    // handler method to handle user registration request
    @GetMapping("/register")
    public String showRegistrationForm(Model model)
    {
        // Empty Object
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user",user);
        return "blog/register";
    }

    // handler method to handle registration form request
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult bindingResult, Model model)
    {
        User existingUser = userService.findByEmail(user.getEmail());
        if(existingUser != null)
        {
            bindingResult.rejectValue("email",null,"User already exist with same email address");
        }
        if(bindingResult.hasErrors())
        {
            model.addAttribute("user",user);
            return "blog/register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }
}
