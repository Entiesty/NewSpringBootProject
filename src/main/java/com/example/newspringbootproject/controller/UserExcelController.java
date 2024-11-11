package com.example.newspringbootproject.controller;

import com.example.newspringbootproject.pojo.User;
import com.example.newspringbootproject.service.UserService;
import com.example.newspringbootproject.view.UserExcelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/excel")
public class UserExcelController {
    private final UserService userService;
    public UserExcelController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ModelAndView downloadUsers(ModelAndView mav) {
        List<User> userList = userService.list();
        mav.addObject("userList", userList);
        mav.setView(new UserExcelView());

        return mav;
    }
}
