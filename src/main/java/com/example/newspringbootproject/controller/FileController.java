package com.example.newspringbootproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileController {

    @GetMapping("/upload/page")
    public String uploadPage() {
        return "file/upload";
    }

    @PostMapping("/upload/request")
    @ResponseBody
    public Map<String, Object> uploadRequest(HttpServletRequest request) {
        boolean flag = false;
        if(request instanceof MultipartHttpServletRequest mreq) {
            mreq = (MultipartHttpServletRequest) request;
        } else {
            return dealResultMap(false, "上传失败");
        }

        MultipartFile mf = mreq.getFile("file");
        String fileName = mf.getOriginalFilename();
        File file = new File(fileName);
        try {
            mf.transferTo(file);
        } catch (Exception e) {
            return dealResultMap(false, "上传失败");
        }
        return dealResultMap(true, "上传成功");
    }

    @PostMapping("/upload/multipart")
    @ResponseBody
    public Map<String, Object> uploadMultipartFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        File dest = new File(fileName);
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败");
        }
        return dealResultMap(true, "上传成功");
    }

    @PostMapping("/upload/part")
    @ResponseBody
    public Map<String, Object> uploadPart(Part file) {
        String fileName =  file.getSubmittedFileName();
        try {
            file.write(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败");
        }
        return dealResultMap(true, "上传成功");
    }

    private Map<String, Object> dealResultMap(boolean success, String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("msg", msg);

        return result;
    }
}
