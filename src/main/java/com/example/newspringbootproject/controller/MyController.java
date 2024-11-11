package com.example.newspringbootproject.controller;

import com.example.newspringbootproject.pojo.ValidatorPojo;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/my")
public class MyController {

    @GetMapping("/no/annotation")
    @ResponseBody
    public Map<String, Object> noAnnotation(Integer intVal, Long longVal, String strVal) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("intVal", intVal);
        paramsMap.put("longVal", longVal);
        paramsMap.put("strVal", strVal);

        return paramsMap;
    }

    @GetMapping("/annotation")
    @ResponseBody
    public Map<String, Object> requestParam(@RequestParam("int_val") Integer intVal,
                                            @RequestParam("long_val") Long longVal,
                                            @RequestParam("str_val") String strVal) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("intVal", intVal);
        paramsMap.put("longVal", longVal);
        paramsMap.put("strVal", strVal);

        return paramsMap;
    }

    @GetMapping("/request/array")
    @ResponseBody
    public Map<String, Object> requestArray(int[] intArray, Long[] longArray, String[] strArray) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("intArr", intArray);
        paramsMap.put("longArr", longArray);
        paramsMap.put("strArr", strArray);

        return paramsMap;
    }

    @GetMapping("/format/form")
    public String showFormat() {
        return "/format/formatter";
    }

    @PostMapping("/format/commit")
    @ResponseBody
    public Map<String, Object> format(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                      @NumberFormat(pattern = "#,###.##") Double number) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("date", date);
        dataMap.put("number", number);

        return dataMap;
    }

    @GetMapping("/valid/page")
    public String validPage() {
        return "validator";
    }

    @RequestMapping("/valid/validate")
    @ResponseBody
    public Map<String, Object> validate(@Valid @RequestBody ValidatorPojo vp, Errors errors) {
        Map<String, Object> errMap = new HashMap<>();

        List<ObjectError> oes = errors.getAllErrors();
        for (ObjectError oe : oes) {
            String key = null;
            String msg = null;

            if (oe instanceof FieldError fieldError) {
                key = fieldError.getField();
            } else {
                key = oe.getObjectName();
            }
            msg = oe.getDefaultMessage();
            errMap.put(key, msg);
        }
        return errMap;
    }
}
