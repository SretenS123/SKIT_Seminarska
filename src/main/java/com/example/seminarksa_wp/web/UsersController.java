package com.example.seminarksa_wp.web;

import com.example.seminarksa_wp.model.User;
import com.example.seminarksa_wp.model.UserExcelExporter;
import com.example.seminarksa_wp.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsersPage( Model model)
    {
        List<User> users = this.userService.findAll();
        model.addAttribute("users",users);
        model.addAttribute("bodyContent","users");
        return "master-template";
    }
    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id)
    {
        this.userService.deleteById(id);
        return "redirect:/users";
    }
    @GetMapping("/download")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String fileName = "users_" + currentDateTime + ".xlsx";
        String headerValue = "attachment; filename=" + fileName;

        response.setHeader(headerKey,headerValue);
        List<User> userList = this.userService.findAll();

        UserExcelExporter excelExporter  = new UserExcelExporter(userList);
        excelExporter.export(response);

    }
}
