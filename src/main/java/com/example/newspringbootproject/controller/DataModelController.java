package com.example.newspringbootproject.controller;

import com.example.newspringbootproject.pojo.User;
import com.example.newspringbootproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/data")
public class DataModelController {
    private final UserService userService;

    public DataModelController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/model")
    public String useModel(Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);

        return "data/user";
    }

    @GetMapping("/modelMap")
    public ModelAndView useModelMap(Long id, ModelMap modelMap) {
        User user = userService.getById(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("data/user");
        modelMap.put("user", user);

        return mav;
    }

    @GetMapping("/mav")
    public ModelAndView usModelAndView(Long id, ModelAndView mav) {
        User user = userService.getById(id);
        mav.addObject("user", user);
        mav.setViewName("data/user");

        return mav;
    }
}
