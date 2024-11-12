package com.example.newspringbootproject.controller;

import com.example.newspringbootproject.pojo.User;
import com.example.newspringbootproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/request")
public class RequestController {
    private final UserService userService;
    public RequestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/page")
    public String page() {
        return "http/request-header";
    }

    @ResponseBody
    @GetMapping("/user")
    public User getUser(@RequestHeader("id") Long id) {
        return userService.getById(id);
    }
}
