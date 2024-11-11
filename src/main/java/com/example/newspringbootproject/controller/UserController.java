package com.example.newspringbootproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.newspringbootproject.pojo.User;
import com.example.newspringbootproject.service.UserService;
import com.example.newspringbootproject.validator.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/hello")
    public ModelAndView details(Long id) {
        ModelAndView mav = new ModelAndView("user/hello");
        User user = userService.getById(id);
        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping("/details/Json")
    public ModelAndView detailsForJson(Long id) {
        User user = userService.getById(id);
        ModelAndView mav = new ModelAndView();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        mav.setView(jsonView);
        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping("/list")
    public ModelAndView allUsers(Long id) {
        List<User> userList = userService.list();
        ModelAndView mav = new ModelAndView("user/list");
        mav.addObject("users", userList);

        return mav;
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<User> search(String userName) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("user_name", userName);

        return userService.list(userQueryWrapper);
    }

    @GetMapping("/add")
    public String add() {
        return "user/add";
    }

    @PostMapping("/insert")
    @ResponseBody
    public User insert(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User get(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping("/converter")
    @ResponseBody
    public User getUserByConverter(User user) {
        return user;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<User> list(List<User> userList) {
        return userList;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new UserValidator());
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd"), false));
    }

    @GetMapping("/validator")
    @ResponseBody
    public Map<String, Object> validator(@Valid User user, Errors errors, Date date) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("date", DateFormat.getDateInstance().format(date));

        if (errors.hasErrors()) {
            List<ObjectError> oes = errors.getAllErrors();
            for (ObjectError oe : oes) {
                if (oe instanceof FieldError fieldError) {
                    map.put(fieldError.getField(), fieldError.getDefaultMessage());
                } else {
                    map.put(oe.getObjectName(), oe.getDefaultMessage());
                }
            }
        }
        return map;
    }
}
