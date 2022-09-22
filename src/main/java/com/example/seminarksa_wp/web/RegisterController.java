package com.example.seminarksa_wp.web;


import com.example.seminarksa_wp.model.enumerations.Role;
import com.example.seminarksa_wp.model.exceptions.InvalidArgumentsException;
import com.example.seminarksa_wp.model.exceptions.PassworDoNotMatchException;
import com.example.seminarksa_wp.service.AuthService;
import com.example.seminarksa_wp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("bodyContent","register");
        return "master-template";
    }
    @PostMapping
    public String register(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String phoneNumber,
                           @RequestParam String email,
                           @RequestParam("file") MultipartFile file,
                           @RequestParam String password,
                           @RequestParam String reTypePassword,
                           @RequestParam String country,
                           @RequestParam Role role)
    {
        try{
            userService.register1(name,surname,phoneNumber,email,file,password,reTypePassword,country,role);
            return "redirect:/login";
        }
        catch (PassworDoNotMatchException | InvalidArgumentsException exception)
        {
            return "redirect:/reqister?error=" + exception.getMessage();
        }
    }
}
