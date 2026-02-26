package org.cmn.cam66.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Santiago Cristales
 * @date 25/11/2025
 * @project cam66
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Model model, String error, String logout) {
       System.out.println(new BCryptPasswordEncoder().encode("tres"));
        if (error != null) model.addAttribute("errorMsg", "Usuario o contraseña inválidos.");
        if (logout != null) model.addAttribute("msg", "Sesión cerrada correctamente.");
        return "login"; // Thymeleaf template: src/main/resources/templates/login.html
    }

    @GetMapping({"/home", "/"})
    public String home() {
        return "home"; // plantilla de ejemplo
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/user/home")
    public String userHome() {
        return "user-home";
    }
}
