package com.example.newspringbootproject.controller;

import com.example.newspringbootproject.pojo.User;
import com.example.newspringbootproject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes(names = {"user"}, types = Long.class)
@Controller
@RequestMapping("/session")
public class SessionController {
    private final UserService userService;
    public SessionController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/show")
    public String show(@SessionAttribute("id") Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user_id", user.getId());
        model.addAttribute("user", user);

        return "session/details";
    }

    @GetMapping("/setter")
    public String setter(Long id, HttpSession session) {
        session.setAttribute("id", id);

        return "redirect:/session/show";
    }
}
