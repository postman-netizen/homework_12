package org.example.java_pro_homework8.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.java_pro_homework8.services.AdminService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("login")
    public String showLoginForm(){
        return "login";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, HttpServletRequest request) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("_csrf", csrfToken);
        return "admin";
    }


}
